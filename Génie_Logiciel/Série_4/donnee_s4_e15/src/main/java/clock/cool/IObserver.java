package clock.cool;

/**
 *  DESIGN PATTERN : OBSERVER
 *  GOAL : WHEN A SUBJECT CHANGES, OBSERVERS ARE NOTIFIED AND UPDATED
 */
public interface IObserver {

    /**
     * Update is called when an Observable subject changes
     */
    void update();

}
