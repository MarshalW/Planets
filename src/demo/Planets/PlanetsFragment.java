package demo.Planets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import rajawali.RajawaliFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: marshal
 * Date: 13-9-5
 * Time: 下午8:37
 * To change this template use File | Settings | File Templates.
 */
public class PlanetsFragment extends RajawaliFragment {

    private static Map<String, MapItem> typeMap = new HashMap<String, MapItem>();

    private static class MapItem {
        public int surface, normal;

        public MapItem(int surface, int normal) {
            this.surface = surface;
            this.normal = normal;
        }
    }

    static {
        typeMap.put("earth", new MapItem(R.drawable.earth_surface, R.drawable.earth_normal));
        typeMap.put("mars", new MapItem(R.drawable.mars_surface, R.drawable.mars_normal));
    }

    private PlanetsRenderer renderer;

    private MapItem mapItem;

    public PlanetsFragment(String type) {
        this.mapItem=typeMap.get(type);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderer = new PlanetsRenderer(this.getActivity());
        renderer.setSurfaceView(mSurfaceView);
        renderer.setMap(mapItem.surface,mapItem.normal);
        setRenderer(renderer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayout = (FrameLayout) inflater.inflate(R.layout.planets,
                container, false);
        mLayout.addView(mSurfaceView);

        return mLayout;
    }

    @Override
    public void onDestroy() {
        try {
            super.onDestroy();
        } catch (Exception e) {
        }
        renderer.onSurfaceDestroyed();
    }
}
