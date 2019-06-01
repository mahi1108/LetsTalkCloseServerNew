package com.mLabsCloseServer.LetsTalk.viewHolders;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.mLabsCloseServer.LetsTalk.utils.CryptLib;
import com.vanniktech.emoji.EmojiTextView;
import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.interfaces.OnMessageItemClick;
import com.mLabsCloseServer.LetsTalk.models.Message;
import com.mLabsCloseServer.LetsTalk.utils.GeneralUtils;
import com.mLabsCloseServer.LetsTalk.utils.LinkTransformationMethod;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import static com.mLabsCloseServer.LetsTalk.adapters.MessageAdapter.MY;

/**
 * Created by mayank on 11/5/17.
 */

public class MessageTextViewHolder extends com.mLabsCloseServer.LetsTalk.viewHolders.BaseMessageViewHolder {
    EmojiTextView text;
    LinearLayout ll;

    private Message message;

    private static int _4dpInPx = -1;

    public MessageTextViewHolder(View itemView, View newMessageView, OnMessageItemClick itemClickListener) {
        super(itemView, newMessageView, itemClickListener);
        text = itemView.findViewById(R.id.text);
        ll = itemView.findViewById(R.id.container);

        text.setTransformationMethod(new LinkTransformationMethod());
        text.setMovementMethod(LinkMovementMethod.getInstance());
        if (_4dpInPx == -1) _4dpInPx = GeneralUtils.dpToPx(itemView.getContext(), 4);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(true);
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClick(false);
                return true;
            }
        });
    }

    @Override
    public void setData(Message message, int position) {
        super.setData(message, position);
        this.message = message;
        cardView.setCardBackgroundColor(ContextCompat.getColor(context, message.isSelected() ? R.color.colorPrimary : R.color.colorBgLight));
        ll.setBackgroundColor(message.isSelected() ? ContextCompat.getColor(context, R.color.colorPrimary) : isMine() ? Color.WHITE : ContextCompat.getColor(context, R.color.colorBgLight));
        CryptLib cryptLib = null;
        String body = "new message";
        try {
            cryptLib = new CryptLib();
            body = cryptLib.decryptCipherTextWithRandomIV(message.getBody(), message.getId());

        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        text.setText(body);
        if (getItemViewType() == MY) {
            animateView(position);
        }
    }

    private void animateView(int position) {
        if (animate && position > lastPosition) {

            itemView.post(new Runnable() {
                @Override
                public void run() {

                    float originalX = cardView.getX();
                    final float originalY = itemView.getY();
                    int[] loc = new int[2];
                    newMessageView.getLocationOnScreen(loc);
                    cardView.setX(loc[0] / 2);
                    itemView.setY(loc[1]);
                    ValueAnimator radiusAnimator = new ValueAnimator();
                    radiusAnimator.setFloatValues(80, _4dpInPx);
                    radiusAnimator.setDuration(850);
                    radiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            cardView.setRadius((Float) animation.getAnimatedValue());
                        }
                    });
                    radiusAnimator.start();
                    cardView.animate().x(originalX).setDuration(900).setInterpolator(new DecelerateInterpolator()).start();
                    itemView.animate().y(originalY - _4dpInPx).setDuration(750).setInterpolator(new DecelerateInterpolator()).start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            itemView.animate().y(originalY + _4dpInPx).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        }
                    }, 750);
                }
            });
            lastPosition = position;
//            setAnimation(getAdapterPosition());
            animate = false;
        }
    }

}
