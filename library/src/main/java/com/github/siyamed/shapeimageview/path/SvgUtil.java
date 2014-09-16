package com.github.siyamed.shapeimageview.path;

import android.content.Context;

import com.github.siyamed.shapeimageview.path.parser.IoUtil;
import com.github.siyamed.shapeimageview.path.parser.PathInfo;
import com.github.siyamed.shapeimageview.path.parser.SvgParser;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SvgUtil {
    private static final Map<Integer, PathInfo> PATH_MAP = new ConcurrentHashMap<Integer, PathInfo>();

    public static final PathInfo readSvg(Context context, int resId) {
        PathInfo pathInfo = PATH_MAP.get(resId);
        if(pathInfo == null) {
            InputStream is = null;
            try {
                is = context.getResources().openRawResource(resId);
                pathInfo = SvgParser.getSVGFromInputStream(is);
            } finally {
                IoUtil.closeQuitely(is);
            }
        }

        return pathInfo;
    }
}
