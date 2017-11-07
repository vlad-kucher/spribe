package vlad.kucher.spribe.util.exception;

/**
 * Created by Vlad on 07.11.2017.
 */
public class ErrorInfo {
    public final String url;
    public final String exception;

    public ErrorInfo(String url, Throwable t) {
        this.url = url;
        this.exception = t.getLocalizedMessage();
    }
}
