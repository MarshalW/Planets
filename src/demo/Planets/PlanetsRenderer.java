package demo.Planets;

import android.content.Context;
import rajawali.Object3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.NormalMapTexture;
import rajawali.materials.textures.Texture;
import rajawali.math.vector.Vector3;
import rajawali.primitives.Sphere;
import rajawali.renderer.RajawaliRenderer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: marshal
 * Date: 13-9-4
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class PlanetsRenderer extends RajawaliRenderer {

    private Object3D mSphere;

    private int surface, normal;

    public PlanetsRenderer(Context context) {
        super(context);
        setFrameRate(60);
    }

    public void initScene() {
        super.initScene();

        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setPower(2.5f);
        directionalLight.setDirection(new Vector3(-1, -0.3f, -0.4));
        getCurrentScene().addLight(directionalLight);

        try {
            Material material = new Material();
            material.setDiffuseMethod(new DiffuseMethod.Lambert());
            material.addTexture(new Texture("surfaceTexture",
                    surface));
            if (normal != 0) {
                material.addTexture(new NormalMapTexture("normalTexture", normal));
            }

            mSphere = new Sphere(1, 50, 50);
            mSphere.setMaterial(material);
            getCurrentScene().addChild(mSphere);
            material.enableLighting(true);
        } catch (ATexture.TextureException e) {
            e.printStackTrace();
        }

        getCurrentCamera().setPosition(0, 2, 6);
        getCurrentCamera().setLookAt(0, 0, 0);
    }

    public void setMap(int surface, int normal) {
        this.surface = surface;
        this.normal = normal;
    }

    public void onDrawFrame(GL10 glUnused) {
        super.onDrawFrame(glUnused);
        mSphere.setRotY(mSphere.getRotY() - 0.1);
    }
}
