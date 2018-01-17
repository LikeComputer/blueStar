package me.tatarka.bindingcollectionadapter2;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Helper databinding utilities. May be made public some time in the future if they prove to be
 * useful.
 */
public class Utils {
    private static final String TAG = "EasyBinding";
    public static boolean sInDebug = true;
    private static Context sContext;

    public static void care(Context context){
        sContext = context;
    }

    public static Context getContext(){
        return sContext;
    }

    /**
     * Helper to throw an exception when {@link android.databinding.ViewDataBinding#setVariable(int, Object Object)} returns false.
     */
    public static void throwMissingVariable(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes){
        Context context = binding.getRoot().getContext();
        Resources resources = context.getResources();
        String layoutName = resources.getResourceName(layoutRes);
        String bindingVariableName = DataBindingUtil.convertBrIdToString(bindingVariable);
        throw new IllegalStateException("Could not bind variable '"+bindingVariableName+"' in layout '"+layoutName+"'");
    }

    /**
     * Ensures the call was made on the main thread. This is enforced for all ObservableList change
     * operations.
     */
    public static void ensureChangeOnMainThread(){
        if(Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
        }
    }

    /**
     * Constructs a binding adapter class from it's class name using reflection.
     */
    @SuppressWarnings("unchecked")
    public static <T, A extends BindingCollectionAdapter<T>> A createClass(Class<? extends BindingCollectionAdapter> adapterClass, ItemBinding<T> itemBinding){
        try {
            return (A)adapterClass.getConstructor(ItemBinding.class).newInstance(itemBinding);
        }catch(Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    /**
     * http://blog.csdn.net/liuxu0703/article/details/70145168
     * try get host activity from view.
     * views hosted on floating window like dialog and toast will sure return null.
     *
     * @return host activity; or null if not available
     * @from: https://stackoverflow.com/questions/8276634/android-get-hosting-activity-from-a-view/32973351#32973351
     */
    public static Activity getAct4View(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if(context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static void startAct4View(View view,Intent intent) {
        Activity act4View = getAct4View(view);
        if(act4View != null) {
            act4View.startActivity(intent);
        }
    }

    /**
     * dip转为PX
     */
    public static int dp2px(float dipValue){
        float fontScale = Resources.getSystem().getDisplayMetrics().density;
        return (int)( dipValue*fontScale+0.5f );
    }

    /**
     * px转dip
     * @param pxValue
     * @return
     */
    public static int px2dp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void LOG(Object... msg){
        if(sInDebug) {
            StringBuilder sb = new StringBuilder();
            for(Object o : msg) {
                if(o != null) {
                    sb.append(o.toString()).append("   ");
                }
            }
            Log.d(TAG, sb.toString());
        }
    }

    public static SpannableString hightLightStrParser(@NonNull SpannableString orign, String key, int keyColor){
        if(!TextUtils.isEmpty(orign) && !TextUtils.isEmpty(key)) {
            try {
                Pattern p = Pattern.compile(new StringBuilder("[").append(key).append("]").toString());
                Matcher m = p.matcher(orign);
                while(m.find()) {
                    int start = m.start();
                    int end = m.end();
                    if(!TextUtils.isEmpty(m.group())) {
                        orign.setSpan(new ForegroundColorSpan(keyColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }catch(PatternSyntaxException e) {
                LOG(Log.getStackTraceString(e));
            }
        }
        return orign;
    }
}
