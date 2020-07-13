package mimly.brown;

import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mimly.brown.view.Particle2D;

public class TraceableParticleListAdapter extends RecyclerView.Adapter<TraceableParticleListAdapter.TraceableParticleHolder> {

    public class TraceableParticleHolder extends RecyclerView.ViewHolder {

        private final ToggleButton toggleButton;

        public TraceableParticleHolder(@NonNull View itemView) {
            super(itemView);
            this.toggleButton = (ToggleButton) itemView;
            this.toggleButton.setOnClickListener(this::onClick);
        }

        public void onClick(View view) {
            ToggleButton toggleButton = (ToggleButton) view;
            toggleButton.setBackgroundDrawable(view.getContext().getDrawable(
                    toggleButton.isChecked() ? R.drawable.traceable_particle_button_on : R.drawable.traceable_particle_button_off
            ));

            makeTransition(toggleButton);

            Particle2D traceableParticle = traceableParticles.get(getAdapterPosition());
            if (traceableParticle.isBeingTraced()) traceableParticle.setTraceOff();
            else traceableParticle.setTraceOn();
        }

    }

    private final List<Particle2D> traceableParticles;

    public TraceableParticleListAdapter(List<Particle2D> traceableParticles) {
        this.traceableParticles = traceableParticles;
    }

    @NonNull
    @Override
    public TraceableParticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ToggleButton toggleButton = (ToggleButton) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.traceable_particle_button, parent, false);

        return new TraceableParticleHolder(toggleButton);
    }

    @Override
    public void onBindViewHolder(@NonNull TraceableParticleHolder holder, int position) {
        final Resources resources = holder.toggleButton.getContext().getResources();
        holder.toggleButton.setText(String.format(resources.getString(R.string.untracedParticle), position + 1));
        holder.toggleButton.setTextOff(String.format(resources.getString(R.string.untracedParticle), position + 1));
        holder.toggleButton.setTextOn(String.format(resources.getString(R.string.tracedParticle), position + 1));

        Particle2D traceableParticle = traceableParticles.get(position);
        if (traceableParticle.isBeingTraced()) {
            holder.toggleButton.setChecked(true);
            makeTransition(holder.toggleButton);
        } else {
            holder.toggleButton.setChecked(false);
        }
    }

    /**
     * Make transition
     *
     * TRACED     --> from BLACK to COLOR
     * UNTRACED   --> from COLOR to BLACK
     *
     * @param toggleButton
     */
    private void makeTransition(ToggleButton toggleButton) {
        TransitionDrawable transition = (TransitionDrawable) toggleButton.getBackground();
        transition.startTransition(2000);
    }

    @Override
    public int getItemCount() {
        return this.traceableParticles.size();
    }
}
