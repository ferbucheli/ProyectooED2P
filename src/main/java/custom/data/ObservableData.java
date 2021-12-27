
package custom.data;

import java.util.ArrayList;

/** 
 * @param <T> the type that this class holds
 */
public class ObservableData<T> extends ReadOnlyObservableData<T> {

    /**
     * @param initialValue the initial value of the container.
     */
    public ObservableData(T initialValue) {
        super(initialValue);
        this.value = initialValue;
        observers = new ArrayList<>();
    }

    /**
     * @param value the new value to set.
     */
    public synchronized void set(T value) {
        this.emmit(this.value, value);
        this.value = value;
    }

}
