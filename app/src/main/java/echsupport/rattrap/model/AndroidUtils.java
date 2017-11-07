package echsupport.rattrap.model;

/**
 * Created by Emilee on 10/30/17.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.View;

import java.util.List;


public class AndroidUtils {

    public static boolean isNotUiThread() {
        return Thread.currentThread() != Looper.getMainLooper().getThread();
    }


    /**
     * @param view         View to animate
     * @param toVisibility Visibility at the end of animation
     * @param toAlpha      Alpha at the end of animation
     * @param duration     Animation duration in ms
     */
    public static void animateView(final View view, final int toVisibility, float toAlpha, int duration) {
        boolean show = toVisibility == View.VISIBLE;
        if (show) {
            view.setAlpha(0);
        }
        view.setVisibility(View.VISIBLE);
        view.animate()
                .setDuration(duration)
                .alpha(show ? toAlpha : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(toVisibility);
                    }
                });
    }

    /**
     * http://stackoverflow.com/a/15229490/883083
     */
    public static void showDialogFragment(FragmentActivity fragmentActivity, DialogFragment dialogFragment) {
        final FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(dialogFragment, dialogFragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    public static boolean isConnectedToWifiNetwork(Context context) {
        final NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        return netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Show a SnackBar with an action button that dismisses the SnackBar when clicked
     *
     * @param context                  the context where to show
     * @param view                     the parent view of the SnackBar
     * @param stringResId              the text to show in the SnackBar
     * @param dismissButtonStringResId the text to set the action button to
     */
    public static void showSnackbar(Context context, View view, int stringResId, int dismissButtonStringResId) {
        final Snackbar snackbar = Snackbar.make(view, stringResId, Snackbar.LENGTH_SHORT);
        snackbar.setAction(context.getString(dismissButtonStringResId), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    /**
     * Start a new Activity with a transition.
     * <p/>
     * Will start the new Activity without transition if
     * - the Android version does not support it
     * - the transitionView is null
     * - the transitionId is null or empty
     *
     * @param activity       the Activity from where to start the new Activity
     * @param intent         the intent of the new Activity
     * @param requestCode    the request code if the new Activity should be started with, -1 if not needed
     * @param transitionList a list of Pairs that have the necessary information for a transition. The View
     *                       is the starting view from which to start a transition. The String needs to be
     *                       set to the transition id that the starting view and the target view have in
     *                       common.
     */
    public static void startActivityWithTransition(@NonNull Activity activity, @NonNull Intent intent, int requestCode,
                                                   @NonNull List<Pair<View, String>> transitionList) {

        if (!transitionList.isEmpty() &&
                android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions opt;

            if (transitionList.size() == 1) {
                final Pair<View, String> pair = transitionList.get(0);
                opt = ActivityOptions.makeSceneTransitionAnimation(activity, pair.first, pair.second);
            } else {
                opt = ActivityOptions.makeSceneTransitionAnimation(activity,
                        transitionList.toArray(new Pair[transitionList.size()]));
            }

            activity.startActivityForResult(intent, requestCode, opt.toBundle());
            return;
        }
        activity.startActivityForResult(intent, requestCode);
    }
}
