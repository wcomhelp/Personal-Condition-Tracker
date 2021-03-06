package ca.ualberta.cs.personal_condition_tracker;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.graphics.*;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;




public class PinView extends com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView {

        private final Paint paint = new Paint();
        private final PointF vPin = new PointF();
        private PointF sPin;
        private Bitmap pin;

        public PinView(Context context) {
            this(context, null);
        }

        public PinView(Context context, AttributeSet attr) {
            super(context, attr);
            initialise();
        }

        public void setPin(PointF sPin) {
            this.sPin = sPin;
            initialise();
            invalidate();
        }

        private void initialise() {
            float density = getResources().getDisplayMetrics().densityDpi;
            pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.push_pin_red);
            float w = (density/420f) * pin.getWidth();
            float h = (density/420f) * pin.getHeight();
            pin = Bitmap.createScaledBitmap(pin, (int)w, (int)h, true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Don't draw pin before image is ready so it doesn't move around during setup.
            if (!isReady()) {
                return;
            }

            paint.setAntiAlias(true);

            if (sPin != null && pin != null) {
                sourceToViewCoord(sPin, vPin);
                float vX = vPin.x - (pin.getWidth()/2);
                float vY = vPin.y - pin.getHeight();
                canvas.drawBitmap(pin, vX, vY, paint);
            }

        }

    }

