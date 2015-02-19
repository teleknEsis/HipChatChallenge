package com.kmiller.hipchatchallenge.robolectric;

import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

import java.io.File;
import java.io.IOException;

/**
 * Custom test runner because this app targets SDK 21 and Robolectric doesn't like that
 */
public class HipchatTestRunner extends RobolectricTestRunner {

    private static final String PROJECT_DIR = getProjectDirectory();
    private static final String MANIFEST_PROPERTY = PROJECT_DIR + "/src/main/AndroidManifest.xml";
    private static final String RES_PROPERTY = PROJECT_DIR + "/build/intermediates/res/prodenv/debug/";
    private static final String ASSETS_PROPERTY = PROJECT_DIR + "/build/intermediates/assets/prodenv/debug/";
    private static final int TARGET_SDK_VERSION = 18;
    private static final AndroidManifest sAndroidManifest = getAndroidManifest();

    public HipchatTestRunner(final Class<?> testClass) throws Exception {
        super(testClass);
    }

    @Override
    public AndroidManifest getAppManifest(Config config) {
        return sAndroidManifest;
    }

    private static AndroidManifest getAndroidManifest() {
        return new AndroidManifest(Fs.fileFromPath(MANIFEST_PROPERTY), Fs.fileFromPath(RES_PROPERTY), Fs.fileFromPath(ASSETS_PROPERTY)) {
            @Override
            public int getTargetSdkVersion() {
                return TARGET_SDK_VERSION;
            }
        };
    }

    private static String getProjectDirectory() {
        String projectDir = "";
        try {
            File file = new File(".");
            projectDir = file.getCanonicalPath();
        } catch (IOException ignored) {
        }
        return projectDir;
    }
}
