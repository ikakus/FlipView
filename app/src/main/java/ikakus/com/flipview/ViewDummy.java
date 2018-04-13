package ikakus.com.flipview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ikakus on 9/25/17.
 */

class ViewDummy extends View {

    public ViewDummy(Context context, int color) {
        super(context);
        setBackgroundColor(color);
    }

    public ViewDummy(Context context) {
        super(context);
    }

    public ViewDummy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewDummy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewDummy(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
