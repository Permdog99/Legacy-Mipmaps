package permdog99.legacy_mipmaps;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class LegacyMipmapHelper {
    private static final float[] COLOR_FRACTIONS = Util.make(new float[256], (list) -> {
        for(int i = 0; i < list.length; ++i) {
            list[i] = (float)Math.pow((float)i / 255.0F, 2.2);
        }
    });
    public static NativeImage[] getMipmapTU0toTU2(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);
            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i - 1];
                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> 1, nativeImage.getHeight() >> 1, false);

                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();
                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            int color = nativeImage.getColor(l * 2 + 1, m * 2 + 1);
                            int color2 = blend(nativeImage.getColor(l * 2, m * 2), nativeImage.getColor(l * 2 + 1, m * 2), nativeImage.getColor(l * 2, m * 2 + 1), nativeImage.getColor(l * 2, m * 2 + 1), bl);
                            nativeImage2.setColor(l, m, i == 1 || i == 2 ? color : color2);
                        }
                    }
                    nativeImages[i] = nativeImage2;
                }
            }
            return nativeImages;
        }
    }

    public static NativeImage[] getMipmapTU3toTU11(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);
            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i - 1];
                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> 1, nativeImage.getHeight() >> 1, false);

                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();
                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            int color = nativeImage.getColor(l * 2 + 1, m * 2 + 1);
                            int color2 = blend(nativeImage.getColor(l * 2 + 0, m * 2 + 0), nativeImage.getColor(l * 2 + 1, m * 2 + 0), nativeImage.getColor(l * 2 + 0, m * 2 + 1), nativeImage.getColor(l * 2 + 1, m * 2 + 1), bl);
                            nativeImage2.setColor(l, m, i == 1 ? color : color2);
                        }
                    }
                    nativeImages[i] = nativeImage2;
                }
            }
            return nativeImages;
        }
    }

    public static NativeImage[] getMipmapTU12Plus(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);
            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i == 2 ? 0 : i - 1];
//                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> i == 2 ? 2 : 1, nativeImage.getHeight() >> i == 2 ? 2 : 1, false);
                    NativeImage nativeImage2 = new NativeImage(i == 2 ? (nativeImage.getWidth() >> 2) : (nativeImage.getWidth() >> 1), i == 2 ? (nativeImage.getHeight() >> 2) : (nativeImage.getHeight() >> 1), false);

                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();
                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            int colorMipped1 = nativeImage.getColor(i == 2 ? (l * 4 + 2) : (l * 2 + 1), i == 2 ? (m * 4 + 2) : (m * 2 + 1));

                            int colorMipped3 = blend(nativeImage.getColor(l * 2, m * 2 + 1), nativeImage.getColor(l * 2, m * 2 + 1), nativeImage.getColor(l * 2, m * 2 + 1), nativeImage.getColor(l * 2, m * 2 + 1), bl);
                            int colorMipped4 = blend(nativeImage.getColor(l * 2, m * 2), nativeImage.getColor(l * 2 + 1, m * 2), nativeImage.getColor(l * 2, m * 2 + 1), nativeImage.getColor(l * 2, m * 2 + 1), bl);
                            nativeImage2.setColor(l, m, i == 1 ? colorMipped1 : (i == 2 ? colorMipped1 : (i == 3 ? colorMipped3 : colorMipped4)));
                        }
                    }
                    nativeImages[i] = nativeImage2;
                }
            }
            return nativeImages;
        }
    }

    public static NativeImage[] getMipmapJava(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);

            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i - 1];
                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> 1, nativeImage.getHeight() >> 1, false);
                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();

                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            nativeImage2.setColor(l, m, blend(nativeImage.getColor(l * 2 + 0, m * 2 + 0), nativeImage.getColor(l * 2 + 1, m * 2 + 0), nativeImage.getColor(l * 2 + 0, m * 2 + 1), nativeImage.getColor(l * 2 + 1, m * 2 + 1), bl));
                        }
                    }

                    nativeImages[i] = nativeImage2;
                }
            }

            return nativeImages;
        }
    }

    private static boolean hasAlpha(NativeImage image) {
        for(int i = 0; i < image.getWidth(); ++i) {
            for(int j = 0; j < image.getHeight(); ++j) {
                if (image.getColor(i, j) >> 24 == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    private static int blend(int one, int two, int three, int four, boolean checkAlpha) {
        if (checkAlpha) {
            float f = 0.0F;
            float g = 0.0F;
            float h = 0.0F;
            float i = 0.0F;
            if (one >> 24 != 0) {
                f += getColorFraction(one >> 24);
                g += getColorFraction(one >> 16);
                h += getColorFraction(one >> 8);
                i += getColorFraction(one >> 0);
            }

            if (two >> 24 != 0) {
                f += getColorFraction(two >> 24);
                g += getColorFraction(two >> 16);
                h += getColorFraction(two >> 8);
                i += getColorFraction(two >> 0);
            }

            if (three >> 24 != 0) {
                f += getColorFraction(three >> 24);
                g += getColorFraction(three >> 16);
                h += getColorFraction(three >> 8);
                i += getColorFraction(three >> 0);
            }

            if (four >> 24 != 0) {
                f += getColorFraction(four >> 24);
                g += getColorFraction(four >> 16);
                h += getColorFraction(four >> 8);
                i += getColorFraction(four >> 0);
            }

            f /= 4.0F;
            g /= 4.0F;
            h /= 4.0F;
            i /= 4.0F;
            int j = (int)(Math.pow((double)f, 0.45454545454545453) * 255.0);
            int k = (int)(Math.pow((double)g, 0.45454545454545453) * 255.0);
            int l = (int)(Math.pow((double)h, 0.45454545454545453) * 255.0);
            int m = (int)(Math.pow((double)i, 0.45454545454545453) * 255.0);
            if (j < 96) {
                j = 0;
            }

            return j << 24 | k << 16 | l << 8 | m;
        } else {
            int n = getColorComponent(one, two, three, four, 24);
            int o = getColorComponent(one, two, three, four, 16);
            int p = getColorComponent(one, two, three, four, 8);
            int q = getColorComponent(one, two, three, four, 0);
            return n << 24 | o << 16 | p << 8 | q;
        }
    }
    private static int getColorComponent(int one, int two, int three, int four, int bits) {
        float f = getColorFraction(one >> bits);
        float g = getColorFraction(two >> bits);
        float h = getColorFraction(three >> bits);
        float i = getColorFraction(four >> bits);
        float j = (float)((double)((float)Math.pow((double)(f + g + h + i) * 0.25, 0.45454545454545453)));
        return (int)((double)j * 255.0);
    }
    private static float getColorFraction(int value) {
        return COLOR_FRACTIONS[value & 255];
    }

}
