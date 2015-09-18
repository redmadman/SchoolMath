package diondouglas.schoolmath.utils;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import diondouglas.schoolmath.R;

public class RewardAdapter extends BaseAdapter {
    private Context context;
    private final String[] rewardNames;

    public RewardAdapter(Context context, String[] rewardNames){
        this.context = context;
        this.rewardNames = rewardNames;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.rewardgridview, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(rewardNames[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = rewardNames[position];
            String nDrawableName = rewardNames[position].toLowerCase();
            if(context.getResources().getIdentifier(nDrawableName,"drawable",context.getPackageName())!=0){
                int resID = context.getResources().getIdentifier(nDrawableName,"drawable",context.getPackageName());

                imageView.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), resID, 150, 150));
            }
            else{
                Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);
                textView.setText("GIRL_ERROR");
                imageView.setImageResource(R.drawable.girl);
            }


            //TODO FIX THIS - LOGIC FOR REWARDS PICTURES and names... somewhere...

        } else {
            gridView = convertView;
        }

        return gridView;
    }
    @Override
    public int getCount() {
        return rewardNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


}
