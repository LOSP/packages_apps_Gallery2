/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.gallery3d.filtershow.filters;

import com.android.gallery3d.R;

import android.graphics.Bitmap;
import com.android.gallery3d.app.Log;

public class ImageFilterVignette extends SimpleImageFilter {

    private static final String LOGTAG = "ImageFilterVignette";

    public ImageFilterVignette() {
        setFilterType(TYPE_VIGNETTE);
        mName = "Vignette";
    }

    public FilterRepresentation getDefaultRepresentation() {
        FilterBasicRepresentation representation = (FilterBasicRepresentation) super.getDefaultRepresentation();
        representation.setName("Vignette");
        representation.setFilterClass(ImageFilterVignette.class);
        representation.setPriority(TYPE_VIGNETTE);
        return representation;
    }

    @Override
    public int getButtonId() {
        return R.id.vignetteButton;
    }

    @Override
    public int getTextId() {
        return R.string.vignette;
    }

    native protected void nativeApplyFilter(Bitmap bitmap, int w, int h, float strength);

    @Override
    public Bitmap apply(Bitmap bitmap, float scaleFactor, boolean highQuality) {
        if (getParameters() == null) {
            return bitmap;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float value = getParameters().getValue() / 100.0f;
        nativeApplyFilter(bitmap, w, h, value);

        return bitmap;
    }
}
