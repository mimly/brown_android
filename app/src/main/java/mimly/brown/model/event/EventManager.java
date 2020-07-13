package mimly.brown.model.event;

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

public interface EventManager {

    /**
     * Declares methods to add and remove listeners for all model events.
     * <p>
     * -->   addOn<SOME EVENT>Listener
     * -->   removeOn<SOME EVENT>Listener
     */
    void addOnColorOfDeadParticlesUpdateEventListener(final ColorOfDeadParticlesUpdateEventListener colorOfDeadParticlesUpdateEventListener);

    void removeOnColorOfDeadParticlesUpdateEventListener(final ColorOfDeadParticlesUpdateEventListener colorOfDeadParticlesUpdateEventListener);

    void addOnColorOfParticlesUpdateEventListener(final ColorOfParticlesUpdateEventListener colorOfParticlesUpdateEventListener);

    void removeOnColorOfParticlesUpdateEventListener(final ColorOfParticlesUpdateEventListener colorOfParticlesUpdateEventListener);

    void addOnDistanceUpdateEventListener(final DistanceUpdateEventListener distanceUpdateEventListener);

    void removeOnDistanceUpdateEventListener(final DistanceUpdateEventListener distanceUpdateEventListener);

    void addOnMotionStateUpdateEventListener(final MotionStateUpdateEventListener motionStateUpdateEventListener);

    void removeOnMotionStateUpdateEventListener(final MotionStateUpdateEventListener motionStateUpdateEventListener);

    void addOnNumberOfParticlesUpdateEventListener(final NumberOfParticlesUpdateEventListener numberOfParticlesUpdateEventListener);

    void removeOnNumberOfParticlesUpdateEventListener(final NumberOfParticlesUpdateEventListener numberOfParticlesUpdateEventListener);

    void addOnNumberOfTraceableParticlesUpdateEventListener(final NumberOfTraceableParticlesUpdateEventListener numberOfTraceableParticlesUpdateEventListener);

    void removeOnNumberOfTraceableParticlesUpdateEventListener(final NumberOfTraceableParticlesUpdateEventListener numberOfTraceableParticlesUpdateEventListener);

    void addOnRadiusUpdateEventListener(final RadiusUpdateEventListener radiusUpdateEventListener);

    void removeOnRadiusUpdateEventListener(final RadiusUpdateEventListener radiusUpdateEventListener);

    void addOnTimestepUpdateEventListener(final TimestepUpdateEventListener timestepUpdateEventListener);

    void removeOnTimestepUpdateEventListener(final TimestepUpdateEventListener timestepUpdateEventListener);

    /**
     * Triggers an event.
     */
    void notifyOn(final Class<? extends EventListener> eventType, final Model model);

}
