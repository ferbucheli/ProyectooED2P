
package custom.data;

@FunctionalInterface
public interface DataChangeListener<T> {
    void doOnChange(T oldValue, T newValue);
}