package com.guy.class22b_and_9;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;

public class DayView extends FrameLayout {

    public interface OnHourClickListener {
        void onClick(int hour);
    }

    private OnHourClickListener onHourClickListener;

    public void setOnHourClickListener(OnHourClickListener onHourClickListener) {
        this.onHourClickListener = onHourClickListener;
    }


    private boolean hourDividers = true;
    private int dividerColor = Color.BLACK;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    @IntDef({HORIZONTAL, VERTICAL})
    public @interface OrientationMode {}
    private int orientation = 0;

    public void setOrientation(@DayView.OrientationMode int orientation) {
        this.orientation = orientation;
    }

    private ArrayList<View> hoursViews = new ArrayList<>();

    public DayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.DayView);
        orientation = typedArray.getInt(R.styleable.DayView_orientation, DayView.HORIZONTAL);
        hourDividers = typedArray.getBoolean(R.styleable.DayView_hourDividers, true);
        dividerColor = typedArray.getColor(R.styleable.DayView_dividerColor, Color.BLACK);
        typedArray.recycle();

        LinearLayoutCompat lay = new LinearLayoutCompat(context);

        int _orientation = orientation == DayView.HORIZONTAL ? LinearLayoutCompat.HORIZONTAL : LinearLayoutCompat.VERTICAL;
        lay.setOrientation(_orientation);


        if (hourDividers) {
            lay.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);
            int _drawable = orientation == DayView.HORIZONTAL ? R.drawable.divider_vertical : R.drawable.divider_horizontal;
            lay.setDividerDrawable(context.getDrawable(_drawable));
            lay.getDividerDrawable().setTint(dividerColor);
            //lay.getDividerDrawable().setAlpha(80);
        }




        for (int i = 0; i < 24; i++) {
            View hour = new View(context);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(
                    orientation == DayView.HORIZONTAL ? 0 : ViewGroup.LayoutParams.MATCH_PARENT,
                    orientation == DayView.HORIZONTAL ? ViewGroup.LayoutParams.MATCH_PARENT : 0
            );
            params.weight = 1;
            hour.setLayoutParams(params);
            hour.setBackgroundColor(Color.GREEN);


            if (i >= 5 && i <= 8) {
                hour.setBackgroundColor(Color.RED);
            }

            int finalI = i;
            hour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onHourClickListener != null) {
                        onHourClickListener.onClick(finalI);
                    }
                }
            });
            lay.addView(hour);

            hoursViews.add(hour);
        }


        addView(lay);

    }


    public void changeHourColor(int hour, int color) {
        if (hour < 0  ||  hour >= hoursViews.size()) {
            return;
        }

        hoursViews.get(hour).setBackgroundColor(color);
    }

}
