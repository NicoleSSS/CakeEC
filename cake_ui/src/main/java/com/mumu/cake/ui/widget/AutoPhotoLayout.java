package com.mumu.cake.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ui.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * @ClassName: AutoPhotoLayout
 * @Description: 自定义上传
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 10:42
 * @Version: 1.0
 */
public class AutoPhotoLayout extends LinearLayoutCompat {

    private int mCurrentNum = 0;
    private int mMaxNum = 0;
    private int mMaxLineNum = 0;
    private IconTextView mIconAdd = null;
    private LayoutParams mParms = null;
    /**
     * 要删除的图片ID
     */
    private int mDeleteId = 0;
    private AppCompatImageView mTargetImageView = null;
    private int mImageMargin = 0;
    private CakeDelegate mDelegate = null;
    private List<View> mLineViews = null;
    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";
    private float mIconSize = 0;

    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LINE_HEIGHTS = new ArrayList<>();

    /**
     * 防止多次的测量和布局过程
     */
    private boolean mIsOnceInitOnMeasure = false;
    /**
     * s是否已经初始化布局
     */
    private boolean mHasInitOnLayout = false;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);
        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 1);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getDimension(R.styleable.camera_flow_layout_icon_size, 20);
        typedArray.recycle();
    }

    public final void setDelegate(CakeDelegate delegate) {
        this.mDelegate = delegate;
    }

    public final void onCropTarget(Uri uri) {
        createNewImageView();
        Glide.with(mDelegate)
                .load(uri)
                .apply(OPTIONS)
                .into(mTargetImageView);
    }

    private void createNewImageView() {
        mTargetImageView = new AppCompatImageView(getContext());
        mTargetImageView.setId(mCurrentNum);
        mTargetImageView.setLayoutParams(mParms);
        mTargetImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /**获取要删除的图片ID*/
                mDeleteId = v.getId();
                mTargetDialog.show();
                final Window window = mTargetDialog.getWindow();
                if(window != null){
                    window.setContentView(R.layout.dialog_image_click_panel);
                    window.setGravity(Gravity.BOTTOM);
                    window.setWindowAnimations(R.style.anim_pannel_upo_from_bottom);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final WindowManager.LayoutParams params = window.getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    params.dimAmount = 0.5f;
                    window.setAttributes(params);
                    window.findViewById(R.id.dialog_image_clicked_btn_delete)
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /**得到要删除的图片*/
                            final AppCompatImageView deleteImageView =
                                    (AppCompatImageView)findViewById(mDeleteId);
                            /**设置图片逐渐消失的动画*/
                            final AlphaAnimation animation = new AlphaAnimation(1, 0);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            animation.setFillAfter(true);
                            animation.setStartOffset(0);
                            deleteImageView.setAnimation(animation);
                            AutoPhotoLayout.this.removeView(deleteImageView);
                            mCurrentNum -=1;
                            /**当数目达到上限时隐藏添加按钮，不足时显示*/
                            if(mCurrentNum < mMaxNum){
                                mIconAdd.setVisibility(VISIBLE);
                            }
                            mTargetDialog.cancel();
                        }
                    });
                    window.findViewById(R.id.dialog_image_clicked_btn_undetermined)
                            .setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mTargetDialog.cancel();
                                }
                            });
                    window.findViewById(R.id.dialog_image_clicked_btn_cancel)
                            .setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mTargetDialog.cancel();
                                }
                            });
                }
            }
        });
        /**需要在添加子View的时候传入位置*/
        this.addView(mTargetImageView,mCurrentNum);
        mCurrentNum ++;
        /**当添加数目大于mMaxNum时，自动隐藏添加按钮*/
        if(mCurrentNum >= mMaxNum){
            mIconAdd.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int modeWith = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        /**设置wrap_content*/
        int width = 0;
        int height = 0;
        /**记录每一行的宽度与高度*/
        int lineWidth = 0;
        int lineHeight = 0;
        /**得到内部元素个数*/
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final View child = getChildAt(i);
            /**测量子View 的宽高*/
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            /**得到LayoutParams*/
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            /**获取子View占据的宽度*/
            final int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            /**获取子View占据的高度*/
            final int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            /**自动换行*/
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                /**对比得到最大宽度*/
                width = Math.max(width, lineWidth);
                /**重置lineWidth*/
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                /**不换行：叠加行宽*/
                lineWidth += childWidth;
                /**得到当前最大的高度*/
                lineHeight = Math.max(lineHeight, childHeight);
            }
            /**最后一个子View*/
            if (i == cCount - 1) {
                width = Math.max(lineWidth, width);
                /**判断是否超过最大拍照限制*/
                height += lineHeight;
            }
        }
        setMeasuredDimension(
                modeWith == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingRight() + getPaddingLeft(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
        /**设置一行所有图片的宽高*/
        final int imageSideLen = sizeWidth / mMaxLineNum;
        /**只初始化一次*/
        if (!mIsOnceInitOnMeasure) {
            mParms = new LayoutParams(imageSideLen, imageSideLen);
            mIsOnceInitOnMeasure = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /**清除之前的View*/
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        /**当前ViewGroup的宽度*/
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        if (!mHasInitOnLayout) {
            mLineViews = new ArrayList<>();
            mHasInitOnLayout = true;
        }
        final int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            /**如果需要换行*/
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin >
                    width - getPaddingLeft() - getPaddingRight()) {
                /**记录lineHeight*/
                LINE_HEIGHTS.add(lineHeight);
                /**记录当前一行的Views*/
                ALL_VIEWS.add(mLineViews);
                /**重置宽和高h*/
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                /**重置集合*/
                mLineViews.clear();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, lineHeight + lp.topMargin + lp.bottomMargin);
            mLineViews.add(child);
        }
        /**处理最后一行*/
        LINE_HEIGHTS.add(lineHeight);
        ALL_VIEWS.add(mLineViews);
        /**设置子View的位置*/
        int left = getPaddingLeft();
        int top = getPaddingTop();
        /**行数*/
        final int lineNum = ALL_VIEWS.size();
        for (int i = 0; i < lineNum; i++) {
            /**当前行所有的View*/
            mLineViews = ALL_VIEWS.get(i);
            lineHeight = LINE_HEIGHTS.get(i);
            final int size = mLineViews.size();
            for (int j = 0; j < size; j++) {
                final View child = mLineViews.get(j);
                /**判断child的状态*/
                if (child.getVisibility() == GONE) {
                    continue;
                }
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                /**设置子View的边距*/
                final int lc = left + lp.leftMargin;
                final int tc = top + lp.topMargin;
                final int rc = lc + child.getMeasuredWidth() - mImageMargin;
                final int bc = tc + child.getMeasuredHeight();
                /**为子Viw进行布局*/
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin +lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
        mIconAdd.setLayoutParams(mParms);
        /**一定要重新置为false*/
        mHasInitOnLayout = false;
    }

    private void initAddIcon() {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        mIconAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.startCameraWithCheck();
            }
        });
        this.addView(mIconAdd);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }
}
