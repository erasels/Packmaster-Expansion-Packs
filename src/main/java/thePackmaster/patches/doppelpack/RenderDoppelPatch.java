package thePackmaster.patches.doppelpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.scenes.TheCityScene;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.orbs.doppelpack.AbstractDoppel;
import thePackmaster.packs.DoppelPack;

import java.util.Comparator;

@SpirePatch(clz = AbstractPlayer.class, method = "render")
public class RenderDoppelPatch {

    private static final String VERTEX_SHADER = "uniform mat4 u_projTrans;\n" +
            "\n" +
            "attribute vec4 a_position;\n" +
            "attribute vec2 a_texCoord0;\n" +
            "attribute vec4 a_color;\n" +
            "\n" +
            "varying vec4 v_color;\n" +
            "varying vec2 v_texCoord;\n" +
            "\n" +
            "uniform vec2 u_viewportInverse;\n" +
            "\n" +
            "void main() {\n" +
            "    gl_Position = u_projTrans * a_position;\n" +
            "    v_texCoord = a_texCoord0;\n" +
            "    v_color = a_color;\n" +
            "}";

    private static final String FRAGMENT_SHADER = "//SpriteBatch will use texture unit 0\n" +
            "uniform sampler2D u_texture;\n" +
            "uniform sampler2D u_mask;\n" +
            "\n" +
            "uniform vec2 u_uv1;\n" +
            "uniform vec2 u_uv2;\n" +
            "uniform vec2 u_pos1;\n" +
            "uniform vec2 u_pos2;\n" +
            "\n" +
            "//\"in\" varyings from our vertex shader\n" +
            "varying vec4 v_color;\n" +
            "varying vec2 v_texCoord;\n" +
            "\n" +
            "void main() {\n" +
            "    vec4 texColor = texture2D(u_texture, v_texCoord);\n" +
            "    vec2 pixel = v_texCoord;\n" +
            "    vec2 target = (pixel - u_pos1) * (u_uv1 - u_uv2) / (u_pos1 - u_pos2) + u_uv1;\n" +
            "    if (target.x <= 0.14) {\n" +
            "        vec4 maskColor = texture2D(u_mask, target);\n" +
            "        gl_FragColor = texColor * v_color * vec4(1.0, 1.0, 1.0, 1.0 - maskColor.a);\n" +
            "    } else {\n" +
            "        gl_FragColor = texColor * v_color;\n" +
            "    }\n" +
            "}\n";

    private static ShaderProgram shader;
    private static final float[] buffer = new float[8];
    static {
        if (Gdx.files != null) {
            shader = new ShaderProgram(VERTEX_SHADER, FRAGMENT_SHADER);
            if (!shader.isCompiled()) {
                shader = null;
            }
        } else {
            shader = null;
        }
    }

    @SpireInstrumentPatch
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getMethodName().equals("renderHealth")) {
                    m.replace(String.format("{ $_ = $proceed($$); %s.renderDoppelSkeletons(sb); }", RenderDoppelPatch.class.getName()));
                }
            }
        };
    }

    @SuppressWarnings("unused")
    public static void renderDoppelSkeletons(SpriteBatch sb) {
        if (AbstractDungeon.scene instanceof TheCityScene) {
            renderDoppelSkeletonsCity(sb);
        } else {
            renderDoppelSkeletonsDefault(sb);
        }
    }

    private static void renderDoppelSkeletonsDefault(SpriteBatch sb) {
        AbstractDungeon.player.orbs.stream()
                .filter(o -> o instanceof AbstractDoppel)
                .map(o -> (AbstractDoppel)o)
                .sorted(Comparator.comparing(o -> -o.sY))
                .forEach(d -> d.renderSkeleton(sb));
    }

    public static void renderDoppelSkeletonsCity(SpriteBatch sb) {
        if (shader == null) {
            renderDoppelSkeletonsDefault(sb);
        }

        FrameBuffer frameBuffer = DoppelPack.frameBuffer;
        sb.end();
        frameBuffer.begin();
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);
        sb.begin();

        renderDoppelSkeletonsDefault(sb);

        sb.end();
        frameBuffer.end();

        TextureAtlas.AtlasRegion mask = null;
        if (AbstractDungeon.scene instanceof TheCityScene) {
            mask = ReflectionHacks.getPrivate(AbstractDungeon.scene, TheCityScene.class, "mg");
        }

        sb.setShader(shader);
        if (mask != null) {
            mask.getTexture().bind(1);
            buffer[0] = mask.getU();
            buffer[1] = mask.getV2();
            buffer[2] = mask.getU2();
            buffer[3] = mask.getV();
            float scaleX;
            float scaleY;
            if (Settings.isFourByThree) {
                scaleX = Settings.scale;
                scaleY = Settings.yScale;
            } else if (Settings.isLetterbox) {
                scaleX = Settings.xScale;
                scaleY = Settings.xScale;
            } else {
                scaleX = Settings.scale;
                scaleY = Settings.scale;
            }
            buffer[4] = mask.offsetX * scaleX / Settings.WIDTH;
            buffer[5] = mask.offsetY * scaleY / Settings.HEIGHT;
            buffer[6] = mask.packedWidth * scaleX / Settings.WIDTH + buffer[4];
            buffer[7] = mask.packedHeight * scaleY / Settings.HEIGHT + buffer[5];
        }
        TextureRegion frameBufferRegion = new TextureRegion(frameBuffer.getColorBufferTexture());
        frameBufferRegion.getTexture().bind(0);
        sb.begin();
        shader.setUniformi("u_mask", 1);
        shader.setUniform2fv("u_uv1", buffer, 0, 2);
        shader.setUniform2fv("u_uv2", buffer, 2, 2);
        shader.setUniform2fv("u_pos1", buffer, 4, 2);
        shader.setUniform2fv("u_pos2", buffer, 6, 2);

        sb.setColor(Color.WHITE);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        frameBufferRegion.flip(false, true);
        sb.draw(frameBufferRegion, 0, 0);

        sb.end();
        sb.setShader(null);
        sb.begin();
    }
}
