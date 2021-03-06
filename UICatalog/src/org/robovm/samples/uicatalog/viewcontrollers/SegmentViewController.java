/*
 * Copyright (C) 2014 Trillian Mobile AB
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 * Portions of this code is based on Apple Inc's UICatalog sample (v2.11)
 * which is copyright (C) 2008-2013 Apple Inc.
 */

package org.robovm.samples.uicatalog.viewcontrollers;

import java.util.HashSet;
import java.util.Set;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSMutableDictionary;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.NSTextAlignment;
import org.robovm.apple.uikit.UIBarMetrics;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControlEvents;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UIKit;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIScrollView;
import org.robovm.apple.uikit.UISegmentedControl;
import org.robovm.apple.uikit.UISegmentedControlStyle;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewAutoresizing;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.Method;
import org.robovm.samples.uicatalog.Constants;

/**
 * The view controller for hosting the UISegmentedControl features of this
 * sample.
 */
@SuppressWarnings("deprecation")
public class SegmentViewController extends UIViewController {

    private float SegmentedControlHeight = 40.0f;
    private float LabelHeight = 20.0f;

    private UIScrollView scrollView;

    /**
     * reusable method to generate a UILabel to title each segmented control
     * 
     * @param frame
     * @param title
     * @return
     */
    private static UILabel labelWithFrame(CGRect frame, String title) {
        UILabel label = new UILabel(frame);
        label.setTextAlignment(NSTextAlignment.Left);
        label.setText(title);
        label.setFont(UIFont.getBoldSystemFont(17.0));
        label.setTextColor(new UIColor(76.0 / 255.0, 86.0 / 255.0, 108.0 / 255.0, 1.0));
        label.setBackgroundColor(UIColor.colorClear());
        label.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin.set(UIViewAutoresizing.FlexibleRightMargin));
        return label;
    }

    /**
     * Creates all segmented controls and adds them to view
     */
    private void createControls() {

        NSMutableArray<NSString> optionArray = new NSMutableArray<NSString>();
        optionArray.add(new NSString("Check"));
        optionArray.add(new NSString("Search"));
        optionArray.add(new NSString("Tools"));

        double yPlacement = Constants.TOP_MARGIN;
        CGRect frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, this.getView().getBounds().getWidth()
                - (Constants.RIGHT_MARGIN * 2.0), LabelHeight);

        this.scrollView.addSubview(SegmentViewController.labelWithFrame(frame, "UISegmentedControl:"));

        Set<UIImage> images = new HashSet<UIImage>();

        images.add(UIImage.createFromBundle("segment_check.png"));
        images.add(UIImage.createFromBundle("segment_search.png"));
        images.add(UIImage.createFromBundle("segment_tools.png"));

        NSArray<UIImage> imageArray = new NSArray<UIImage>(images);
        UISegmentedControl segmentedControl = new UISegmentedControl(imageArray);

        yPlacement += Constants.TWEEN_MARGIN + LabelHeight;

        frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, this.getView().getBounds().getWidth()
                - (Constants.RIGHT_MARGIN * 2.0), SegmentedControlHeight);
        segmentedControl.setFrame(frame);
        segmentedControl.addTarget(SegmentViewController.this, Selector.register("segmentAction:"),
                UIControlEvents.ValueChanged);
        segmentedControl.setControlStyle(UISegmentedControlStyle.Plain);
        segmentedControl.setSelectedSegment(1);
        segmentedControl.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin
                .set(UIViewAutoresizing.FlexibleRightMargin));

        scrollView.addSubview(segmentedControl);

        // label
        yPlacement += (Constants.TWEEN_MARGIN * 2.0) + SegmentedControlHeight;
        frame = new CGRect(Constants.LEFT_MARGIN,
                yPlacement,
                this.getView().getBounds().getWidth() - (Constants.RIGHT_MARGIN * 2.0),
                LabelHeight);

        this.scrollView.addSubview(SegmentViewController.labelWithFrame(frame, "UISegmentControlStyleBordered:"));

        segmentedControl = new UISegmentedControl(optionArray);// initWithItems:segmentTextContent];
        yPlacement += Constants.TWEEN_MARGIN + LabelHeight;
        frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, this.getView().getBounds().getWidth()
                - (Constants.RIGHT_MARGIN * 2.0), SegmentedControlHeight);
        segmentedControl.setFrame(frame);
        segmentedControl.addTarget(SegmentViewController.this, Selector.register("segmentAction:"),
                UIControlEvents.ValueChanged);
        segmentedControl.setControlStyle(UISegmentedControlStyle.Plain);
        segmentedControl.setSelectedSegment(1);
        segmentedControl.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin
                .set(UIViewAutoresizing.FlexibleRightMargin));
        scrollView.addSubview(segmentedControl);

        // label
        yPlacement += (Constants.TWEEN_MARGIN * 2.0) + SegmentedControlHeight;
        frame = new CGRect(Constants.LEFT_MARGIN,
                yPlacement,
                getView().getBounds().getWidth() - (Constants.RIGHT_MARGIN * 2.0),
                LabelHeight);

        this.scrollView.addSubview(SegmentViewController.labelWithFrame(frame, "UISegmentControlStyleBar:"));

        yPlacement += Constants.TWEEN_MARGIN + LabelHeight;
        segmentedControl = new UISegmentedControl(optionArray);
        frame = new CGRect(Constants.LEFT_MARGIN,
                yPlacement,
                this.getView().getBounds().getWidth() - (Constants.RIGHT_MARGIN * 2.0),
                SegmentedControlHeight);

        segmentedControl.setFrame(frame);
        segmentedControl.addTarget(SegmentViewController.this, Selector.register("segmentAction:"),
                UIControlEvents.ValueChanged);
        segmentedControl.setControlStyle(UISegmentedControlStyle.Bar);
        segmentedControl.setSelectedSegment(1);
        segmentedControl.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin
                .set(UIViewAutoresizing.FlexibleRightMargin));

        this.scrollView.addSubview(segmentedControl);

        yPlacement += (Constants.TWEEN_MARGIN * 2.0) + SegmentedControlHeight;
        frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, getView().getBounds().getWidth() - Constants.RIGHT_MARGIN
                * 2.0, LabelHeight);
        this.scrollView.addSubview(SegmentViewController.labelWithFrame(frame, "UISegmentControlStyleBar: tint"));
        segmentedControl = new UISegmentedControl(optionArray);

        segmentedControl = new UISegmentedControl(optionArray);
        yPlacement += Constants.TWEEN_MARGIN + LabelHeight;
        frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, this.getView().getBounds().getWidth()
                - Constants.RIGHT_MARGIN * 2.0, SegmentedControlHeight);
        segmentedControl.setFrame(frame);
        segmentedControl.addTarget(SegmentViewController.this, Selector.register("segmentAction:"),
                UIControlEvents.ValueChanged);
        segmentedControl.setControlStyle(UISegmentedControlStyle.Bar);
        segmentedControl.setTintColor(new UIColor(0.7, 0.171, 0.1, 1.0));
        segmentedControl.setSelectedSegment(1);
        segmentedControl.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin
                .set(UIViewAutoresizing.FlexibleRightMargin));
        this.scrollView.addSubview(segmentedControl);

        yPlacement += Constants.TWEEN_MARGIN * 2.0 + SegmentedControlHeight;
        frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, this.getView().getBounds().getWidth()
                - Constants.RIGHT_MARGIN * 2.0, LabelHeight);
        this.scrollView.addSubview(SegmentViewController.labelWithFrame(frame, "UISegmentControlStyleBar: image"));

        segmentedControl = new UISegmentedControl(optionArray);
        yPlacement += Constants.TWEEN_MARGIN + LabelHeight;
        frame = new CGRect(Constants.LEFT_MARGIN, yPlacement, this.getView().getBounds().getWidth()
                - (Constants.RIGHT_MARGIN * 2.0), SegmentedControlHeight);
        segmentedControl.setFrame(frame);

        segmentedControl.addTarget(this, Selector.register("segmentAction:"), UIControlEvents.ValueChanged);
        segmentedControl.setControlStyle(UISegmentedControlStyle.Bar);
        segmentedControl.setSelectedSegment(1);
        segmentedControl.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin
                .set(UIViewAutoresizing.FlexibleRightMargin));
        segmentedControl.setBackgroundImage(UIImage.createFromBundle("segmentedBackground.png"), UIControlState.Normal,
                UIBarMetrics.Default);
        segmentedControl.setDividerImage(UIImage.createFromBundle("divider.png"), UIControlState.Normal,
                UIControlState.Normal, UIBarMetrics.Default);

        NSMutableDictionary<NSString, NSObject> textAttributes = new NSMutableDictionary<NSString, NSObject>();
        textAttributes.put(UIKit.ForegroundColorAttributeName(), UIColor.colorBlue());
        textAttributes.put(UIKit.FontAttributeName(), UIFont.getBoldSystemFont(13.0));
        segmentedControl.setTitleTextAttributes(textAttributes, UIControlState.Normal);

        NSMutableDictionary<NSString, NSObject> textHighlightedAttributes = new NSMutableDictionary<NSString, NSObject>();
        textHighlightedAttributes.put(UIKit.ForegroundColorAttributeName(), UIColor.colorRed());
        textHighlightedAttributes.put(UIKit.FontAttributeName(), UIFont.getBoldSystemFont(13.0));
        segmentedControl.setTitleTextAttributes(textHighlightedAttributes, UIControlState.Highlighted);

        this.scrollView.addSubview(segmentedControl);
        this.getView().addSubview(this.scrollView);

    }

    @Method
    void segmentAction(long segmentId) {
        // NSLog(@"segmentAction: selected segment = %d", [sender
        // selectedSegmentIndex]);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        this.setTitle("SegmentTitle");

        this.setView(new UIView(new CGRect(0, 0, 320, 460)));
        this.scrollView = new UIScrollView(new CGRect(0, 0, 320, 460));
        this.scrollView.setContentSize(new CGSize(this.scrollView.getFrame().getWidth(), this.scrollView.getFrame()
                .getHeight() - this.getNavigationController().getNavigationBar().getFrame().getHeight() + 80));

        this.createControls(); // create the showcase of segment controls
    }
}
