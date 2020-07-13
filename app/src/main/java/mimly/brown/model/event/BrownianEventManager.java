package mimly.brown.model.event;

import java.util.ArrayList;
import java.util.List;

import mimly.brown.model.Model;
import mimly.brown.model.event.listener.ColorOfDeadParticlesUpdateEventListener;
import mimly.brown.model.event.listener.ColorOfParticlesUpdateEventListener;
import mimly.brown.model.event.listener.DistanceUpdateEventListener;
import mimly.brown.model.event.listener.EventListener;
import mimly.brown.model.event.listener.MotionStateUpdateEventListener;
import mimly.brown.model.event.listener.NumberOfParticlesUpdateEventListener;
import mimly.brown.model.event.listener.NumberOfTraceableParticlesUpdateEventListener;
import mimly.brown.model.event.listener.RadiusUpdateEventListener;
import mimly.brown.model.event.listener.TimestepUpdateEventListener;

public class BrownianEventManager implements EventManager {

    private final List<EventListener> listeners = new ArrayList<>();

    @Override
    public void addOnColorOfDeadParticlesUpdateEventListener(ColorOfDeadParticlesUpdateEventListener colorOfDeadParticlesUpdateEventListener) {
        this.listeners.add(colorOfDeadParticlesUpdateEventListener);
    }

    @Override
    public void removeOnColorOfDeadParticlesUpdateEventListener(ColorOfDeadParticlesUpdateEventListener colorOfDeadParticlesUpdateEventListener) {
        this.listeners.remove(colorOfDeadParticlesUpdateEventListener);
    }

    @Override
    public void addOnColorOfParticlesUpdateEventListener(ColorOfParticlesUpdateEventListener colorOfParticlesUpdateEventListener) {
        this.listeners.add(colorOfParticlesUpdateEventListener);
    }

    @Override
    public void removeOnColorOfParticlesUpdateEventListener(ColorOfParticlesUpdateEventListener colorOfParticlesUpdateEventListener) {
        this.listeners.remove(colorOfParticlesUpdateEventListener);
    }

    @Override
    public void addOnDistanceUpdateEventListener(DistanceUpdateEventListener distanceUpdateEventListener) {
        this.listeners.add(distanceUpdateEventListener);
    }

    @Override
    public void removeOnDistanceUpdateEventListener(DistanceUpdateEventListener distanceUpdateEventListener) {
        this.listeners.remove(distanceUpdateEventListener);
    }

    @Override
    public void addOnMotionStateUpdateEventListener(MotionStateUpdateEventListener motionStateUpdateEventListener) {
        this.listeners.add(motionStateUpdateEventListener);
    }

    @Override
    public void removeOnMotionStateUpdateEventListener(MotionStateUpdateEventListener motionStateUpdateEventListener) {
        this.listeners.remove(motionStateUpdateEventListener);
    }

    @Override
    public void addOnNumberOfParticlesUpdateEventListener(NumberOfParticlesUpdateEventListener numberOfParticlesUpdateEventListener) {
        this.listeners.add(numberOfParticlesUpdateEventListener);
    }

    @Override
    public void removeOnNumberOfParticlesUpdateEventListener(NumberOfParticlesUpdateEventListener numberOfParticlesUpdateEventListener) {
        this.listeners.remove(numberOfParticlesUpdateEventListener);
    }

    @Override
    public void addOnNumberOfTraceableParticlesUpdateEventListener(NumberOfTraceableParticlesUpdateEventListener numberOfTraceableParticlesUpdateEventListener) {
        this.listeners.add(numberOfTraceableParticlesUpdateEventListener);
    }

    @Override
    public void removeOnNumberOfTraceableParticlesUpdateEventListener(NumberOfTraceableParticlesUpdateEventListener numberOfTraceableParticlesUpdateEventListener) {
        this.listeners.remove(numberOfTraceableParticlesUpdateEventListener);
    }

    @Override
    public void addOnRadiusUpdateEventListener(RadiusUpdateEventListener radiusUpdateEventListener) {
        this.listeners.add(radiusUpdateEventListener);
    }

    @Override
    public void removeOnRadiusUpdateEventListener(RadiusUpdateEventListener radiusUpdateEventListener) {
        this.listeners.remove(radiusUpdateEventListener);
    }

    @Override
    public void addOnTimestepUpdateEventListener(TimestepUpdateEventListener timestepUpdateEventListener) {
        this.listeners.add(timestepUpdateEventListener);
    }

    @Override
    public void removeOnTimestepUpdateEventListener(TimestepUpdateEventListener timestepUpdateEventListener) {
        this.listeners.remove(timestepUpdateEventListener);
    }

    @Override
    public void notifyOn(final Class<? extends EventListener> eventType, final Model model) {
        this.listeners.stream().filter(eventType::isInstance).forEach(event -> event.onUpdate(model));
    }

}
