package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.C0039R;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

public class ActionBarContextView extends AbsActionBarView {
    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public /* bridge */ /* synthetic */ void animateToVisibility(int i) {
        super.animateToVisibility(i);
    }

    public /* bridge */ /* synthetic */ boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return super.setupAnimatorToVisibility(i, j);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attrs) {
        this(context, attrs, C0039R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, C0039R.styleable.ActionMode, defStyle, 0);
        ViewCompat.setBackground(this, a.getDrawable(C0039R.styleable.ActionMode_background));
        this.mTitleStyleRes = a.getResourceId(C0039R.styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = a.getResourceId(C0039R.styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = a.getLayoutDimension(C0039R.styleable.ActionMode_height, 0);
        this.mCloseItemLayout = a.getResourceId(C0039R.styleable.ActionMode_closeItemLayout, C0039R.layout.abc_action_mode_close_item_material);
        a.recycle();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public void setContentHeight(int height) {
        this.mContentHeight = height;
    }

    public void setCustomView(View view) {
        View view2 = this.mCustomView;
        if (view2 != null) {
            removeView(view2);
        }
        this.mCustomView = view;
        if (view != null) {
            LinearLayout linearLayout = this.mTitleLayout;
            if (linearLayout != null) {
                removeView(linearLayout);
                this.mTitleLayout = null;
            }
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        initTitle();
    }

    public void setSubtitle(CharSequence subtitle) {
        this.mSubtitle = subtitle;
        initTitle();
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    private void initTitle() {
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(C0039R.layout.abc_action_bar_title_item, this);
            this.mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(C0039R.C0041id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(C0039R.C0041id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean hasTitle = !TextUtils.isEmpty(this.mTitle);
        boolean hasSubtitle = !TextUtils.isEmpty(this.mSubtitle);
        int i = 0;
        this.mSubtitleView.setVisibility(hasSubtitle ? 0 : 8);
        LinearLayout linearLayout = this.mTitleLayout;
        if (!hasTitle && !hasSubtitle) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    public void initForMode(final ActionMode mode) {
        View view = this.mClose;
        if (view == null) {
            this.mClose = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
            addView(this.mClose);
        } else if (view.getParent() == null) {
            addView(this.mClose);
        }
        this.mClose.findViewById(C0039R.C0041id.action_mode_close_button).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mode.finish();
            }
        });
        MenuBuilder menu = (MenuBuilder) mode.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        menu.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        ViewCompat.setBackground(this.mMenuView, null);
        addView(this.mMenuView, layoutParams);
    }

    public void closeMode() {
        if (this.mClose == null) {
            killMode();
        }
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }

    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int customHeightMode = 1073741824;
        if (widthMode != 1073741824) {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
            throw new IllegalStateException(sb.toString());
        } else if (MeasureSpec.getMode(heightMeasureSpec) != 0) {
            int contentWidth = MeasureSpec.getSize(widthMeasureSpec);
            if (this.mContentHeight > 0) {
                maxHeight = this.mContentHeight;
            } else {
                maxHeight = MeasureSpec.getSize(heightMeasureSpec);
            }
            int verticalPadding = getPaddingTop() + getPaddingBottom();
            int availableWidth = (contentWidth - getPaddingLeft()) - getPaddingRight();
            int height = maxHeight - verticalPadding;
            int childSpecHeight = MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE);
            View view = this.mClose;
            int i = 0;
            if (view != null) {
                int availableWidth2 = measureChildView(view, availableWidth, childSpecHeight, 0);
                MarginLayoutParams lp = (MarginLayoutParams) this.mClose.getLayoutParams();
                availableWidth = availableWidth2 - (lp.leftMargin + lp.rightMargin);
            }
            if (this.mMenuView != null && this.mMenuView.getParent() == this) {
                availableWidth = measureChildView(this.mMenuView, availableWidth, childSpecHeight, 0);
            }
            LinearLayout linearLayout = this.mTitleLayout;
            if (linearLayout != null && this.mCustomView == null) {
                if (this.mTitleOptional) {
                    this.mTitleLayout.measure(MeasureSpec.makeMeasureSpec(0, 0), childSpecHeight);
                    int titleWidth = this.mTitleLayout.getMeasuredWidth();
                    boolean titleFits = titleWidth <= availableWidth;
                    if (titleFits) {
                        availableWidth -= titleWidth;
                    }
                    LinearLayout linearLayout2 = this.mTitleLayout;
                    if (!titleFits) {
                        i = 8;
                    }
                    linearLayout2.setVisibility(i);
                } else {
                    availableWidth = measureChildView(linearLayout, availableWidth, childSpecHeight, 0);
                }
            }
            View view2 = this.mCustomView;
            if (view2 != null) {
                LayoutParams lp2 = view2.getLayoutParams();
                int customWidthMode = lp2.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                int customWidth = lp2.width >= 0 ? Math.min(lp2.width, availableWidth) : availableWidth;
                if (lp2.height == -2) {
                    customHeightMode = Integer.MIN_VALUE;
                }
                int i2 = widthMode;
                this.mCustomView.measure(MeasureSpec.makeMeasureSpec(customWidth, customWidthMode), MeasureSpec.makeMeasureSpec(lp2.height >= 0 ? Math.min(lp2.height, height) : height, customHeightMode));
            }
            if (this.mContentHeight <= 0) {
                int measuredHeight = 0;
                int count = getChildCount();
                for (int i3 = 0; i3 < count; i3++) {
                    int paddedViewHeight = getChildAt(i3).getMeasuredHeight() + verticalPadding;
                    if (paddedViewHeight > measuredHeight) {
                        measuredHeight = paddedViewHeight;
                    }
                }
                setMeasuredDimension(contentWidth, measuredHeight);
                return;
            }
            setMeasuredDimension(contentWidth, maxHeight);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getClass().getSimpleName());
            sb2.append(" can only be used with android:layout_height=\"wrap_content\"");
            throw new IllegalStateException(sb2.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        int x;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int x2 = isLayoutRtl ? (r - l) - getPaddingRight() : getPaddingLeft();
        int y = getPaddingTop();
        int contentHeight = ((b - t) - getPaddingTop()) - getPaddingBottom();
        View view = this.mClose;
        if (view == null || view.getVisibility() == 8) {
            x = x2;
        } else {
            MarginLayoutParams lp = (MarginLayoutParams) this.mClose.getLayoutParams();
            int endMargin = isLayoutRtl ? lp.leftMargin : lp.rightMargin;
            int x3 = next(x2, isLayoutRtl ? lp.rightMargin : lp.leftMargin, isLayoutRtl);
            x = next(x3 + positionChild(this.mClose, x3, y, contentHeight, isLayoutRtl), endMargin, isLayoutRtl);
        }
        LinearLayout linearLayout = this.mTitleLayout;
        if (!(linearLayout == null || this.mCustomView != null || linearLayout.getVisibility() == 8)) {
            x += positionChild(this.mTitleLayout, x, y, contentHeight, isLayoutRtl);
        }
        View view2 = this.mCustomView;
        if (view2 != null) {
            int x4 = x + positionChild(view2, x, y, contentHeight, isLayoutRtl);
        }
        int x5 = isLayoutRtl ? getPaddingLeft() : (r - l) - getPaddingRight();
        if (this.mMenuView != null) {
            int x6 = x5 + positionChild(this.mMenuView, x5, y, contentHeight, !isLayoutRtl);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 32) {
            event.setSource(this);
            event.setClassName(getClass().getName());
            event.setPackageName(getContext().getPackageName());
            event.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(event);
    }

    public void setTitleOptional(boolean titleOptional) {
        if (titleOptional != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = titleOptional;
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }
}
