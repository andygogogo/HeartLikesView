package com.app.android.heartlikeslayout;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FloatAnimation extends Animation {

    private PathMeasure mPm;
    private View mView;
    private float mDistance;
    private float mRotation;

    public FloatAnimation(Path path, float rotation, View parent, View child) {
        mPm = new PathMeasure(path, false);
        mDistance = mPm.getLength();
        mView = child;
        mRotation = rotation;
        parent.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void applyTransformation(float factor, Transformation transformation) {
        Matrix matrix = transformation.getMatrix();
        mPm.getMatrix(mDistance * factor, matrix, PathMeasure.POSITION_MATRIX_FLAG);
        mView.setRotation(mRotation * factor);
        float scale = 1F;
        if (3000.0F * factor < 200.0F) {
            scale = scale(factor, 0.0D, 0.066D, 0.2D, 1.1D);
        } else if (3000.0F * factor < 300.0F) {
            scale = scale(factor, 0.066D, 0.1D, 1.1D, 1.0D);
        }
        mView.setScaleX(scale);
        mView.setScaleY(scale);
        transformation.setAlpha(1.0F - factor);
    }

    private float scale(double a, double b, double c, double d, double e) {
        return (float) ((a - b) / (c - b) * (e - d) + d);
    }

}
