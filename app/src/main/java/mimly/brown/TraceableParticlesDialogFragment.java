package mimly.brown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import mimly.brown.controller.Controller;

public class TraceableParticlesDialogFragment extends DialogFragment {

    private Controller controller = null;

    public TraceableParticlesDialogFragment() {
        // Required empty public constructor
    }

    public TraceableParticlesDialogFragment(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_traceable_particles, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.Adapter<TraceableParticleListAdapter.TraceableParticleHolder> adapter =
                new TraceableParticleListAdapter(
                        this.controller != null ? this.controller.getTraceableParticles() : Collections.emptyList()
                );
        recyclerView.setAdapter(adapter);
    }

}