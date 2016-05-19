package com.lockersoft.battleship2016;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Dave.Jones on 5/16/2016.
 */
public class BoardView extends ImageView {

  Integer screenHeight, screenWidth;
  Paint paint;

  public BoardView( Context context, AttributeSet attrs ) {
    super( context, attrs );
    paint = new Paint();
    paint.setStrokeWidth( 5 );
    paint.setColor( Color.BLUE );
    paint.setStyle( Paint.Style.FILL_AND_STROKE );
    paint.setAntiAlias( true );

    // Set the paint font
    Typeface typeface = Typeface.create( Typeface.SANS_SERIF, Typeface.BOLD );
    paint.setTypeface( typeface );
  }

  @Override
  protected void onDraw( Canvas canvas ) {
    screenHeight = canvas.getHeight();
    screenWidth = canvas.getWidth();

    paint.setColor( Color.CYAN );
    canvas.drawRect( 0, 0, screenWidth, screenWidth, paint );
    paint.setColor( Color.BLACK );

    // 11 columns across, 11 rows down

    canvas.drawLine( 2, 0, 0, screenWidth, paint );
    canvas.drawLine( screenWidth - 2, 0, screenWidth - 2, screenWidth, paint );
    int cellWidth = screenWidth / 11;
    paint.setTextSize( cellWidth );

    for( int i = 0; i < 12; i++ ) {
      canvas.drawLine( ( i * cellWidth ), 0, ( i * cellWidth ), screenWidth, paint );
      canvas.drawLine( 0, ( i * cellWidth ), screenWidth, ( i * cellWidth ), paint );
    }
    Rect bounds = new Rect();
    paint.getTextBounds( "A", 0, 1, bounds );
    int height = bounds.height();
    int width = bounds.width();

//    canvas.drawText("A",20,(2*cellWidth)-20,paint);
//    int textX = ( cellWidth / 2 ) - ( width / 2 );
    // OR use centered text and just calculate the center of the cell
    paint.setTextAlign( Paint.Align.CENTER );
    int textX = cellWidth / 2;
    int textY = cellWidth + ( ( cellWidth / 2 ) - ( height / 2 ) );
    textY += cellWidth;
    canvas.drawText( "A", textX, textY, paint );
    textY += cellWidth;
    canvas.drawText( "B", textX, textY, paint );

    // Reset textX and textY
    textX = cellWidth + ( cellWidth / 2 );
    textY = cellWidth + ( ( cellWidth / 2 ) - ( height / 2 ) );
    canvas.drawText( "1", textX, textY, paint );
    textX += cellWidth;
    canvas.drawText( "2", textX, textY, paint );
    textX += cellWidth;
    canvas.drawText( "3", textX, textY, paint );
  }
}
