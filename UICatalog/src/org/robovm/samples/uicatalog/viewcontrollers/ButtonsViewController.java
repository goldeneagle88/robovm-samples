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

import java.util.LinkedList;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.foundation.NSMutableAttributedString;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.NSIndexPathExtensions;
import org.robovm.apple.uikit.NSTextAlignment;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlContentHorizontalAlignment;
import org.robovm.apple.uikit.UIControlContentVerticalAlignment;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UIKit;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewCellSelectionStyle;
import org.robovm.apple.uikit.UITableViewController;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewAutoresizing;
import org.robovm.objc.ObjCClass;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.MachineSizedSInt;

/**
 * The table view controller for hosting the UIButton features of this sample.
 */
public class ButtonsViewController extends UITableViewController {

    private static final String DISPLAY_CELL_ID = "DisplayCellID";
    private static final String SOURCE_CELL_ID = "SourceCellID";

    private final int viewTag = 1;

    private final float stdButtonWidth = 106.0f;
    private final float stdButtonHeight = 40.0f;

    private UIButton grayButton;
    private UIButton imageButton;
    private UIButton attrTextButton;
    private UIButton roundedButtonType;
    private UIButton detailDisclosureButtonType;
    private UIButton infoLightButtonType;
    private UIButton infoDarkButtonType;
    private UIButton contactAddButtonType;

    private LinkedList<ListItem> dataSourceArray = new LinkedList<ListItem>();

    /**
     * List item which stores button meta data
     *
     */
    private class ListItem {
        private String sectionTitle;

        private String label;

        private String source;

        private UIView view;

        public ListItem(String sectionTitle, String label, String source,
                UIView view) {
            super();
            this.sectionTitle = sectionTitle;
            this.label = label;
            this.source = source;
            this.view = view;
        }

        public String getSectionTitle() {
            return sectionTitle;
        }

        public String getLabel() {
            return label;
        }

        public String getSource() {
            return source;
        }

        public UIView getView() {
            return view;
        }

    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        // 320, 247
        CGRect tableViewBounds = new CGRect(0.0, 64.0, 320, 247);
        setTableView(new UITableView(tableViewBounds));

        dataSourceArray.add(new ListItem("UIButton", "Background Image", "grayButton", getGrayButton()));
        dataSourceArray.add(new ListItem("UIButton", "Button with Image", "imageButton", getImageButton()));
        dataSourceArray.add(new ListItem("UIButtonTypeRoundedRect", "Rounded Button", "roundedButtonType", getRoundedButtonType()));
        dataSourceArray.add(new ListItem("UIButtonTypeRoundedRect", "Attributet Text", "attrTextButton", getAttrTextButton()));
        dataSourceArray.add(new ListItem("UIButtonTypeDetailDisclosure", "Detail Disclosure", "detailDisclosureButton", getDetailDisclosureButtonType()));
        dataSourceArray.add(new ListItem("UIButtonTypeInfoLight", "Info Light", "attrTextButton", getInfoLightButtonType()));
        dataSourceArray.add(new ListItem("UIButtonTypeRoundDark", "Info Dark", "attrTextButton", getInfoDarkButtonType()));
        dataSourceArray.add(new ListItem("UIButtonTypeContactAdd", "Add Contact", "contactAddButton", getContactAddButtonType()));

        // register our cell IDs for later when we are asked for
        // UITableViewCells
        getTableView().registerReusableCellClass(ObjCClass.getByType(UITableViewCell.class), DISPLAY_CELL_ID);
        getTableView().registerReusableCellClass(ObjCClass.getByType(UITableViewCell.class), SOURCE_CELL_ID);

    }

    @Override
    public @MachineSizedSInt long getNumberOfSections(UITableView tableView) {
        return this.dataSourceArray.size();
    }

    @Override
    public String getSectionHeaderTitle(UITableView tableView,
            @MachineSizedSInt long section) {
        return this.dataSourceArray.get((int) section).getSectionTitle();
    }

    @Override
    public @MachineSizedSInt long getNumberOfRowsInSection(UITableView tableView,
            @MachineSizedSInt long section) {
        return 2;
    }

    @Override
    public @MachineSizedFloat double getRowHeight(UITableView tableView, NSIndexPath indexPath) {
        return (NSIndexPathExtensions.getRow(indexPath) == 0) ? 50.0 : 38.0;
    }

    @Override
    public UITableViewCell getRowCell(UITableView tableView, NSIndexPath indexPath) {

        UITableViewCell cell = null;
        if (NSIndexPathExtensions.getRow(indexPath) == 0) {
            cell = getTableView().dequeueReusableCell(DISPLAY_CELL_ID, indexPath);
            cell.setSelectionStyle(UITableViewCellSelectionStyle.None);
            UIView viewToRemove = null;
            viewToRemove = cell.getContentView().getViewWithTag(viewTag);
            if (viewToRemove != null) {
                viewToRemove.removeFromSuperview();
            }

            cell.getTextLabel().setText(this.dataSourceArray.get((int) NSIndexPathExtensions.getSection(indexPath)).getLabel());
            UIButton button = (UIButton) this.dataSourceArray.get((int) NSIndexPathExtensions.getSection(indexPath)).getView();
            if (button == null) {
                System.err.println(this.dataSourceArray.get((int) NSIndexPathExtensions.getSection(indexPath)).getLabel());
            }

            CGRect newFrame = button.getFrame();
            newFrame.origin().x(cell.getContentView().getFrame().getWidth() - newFrame.getWidth() - 10.0);
            button.setFrame(newFrame);

            // if the cell is ever resized, keep the button over to the right
            button.setAutoresizingMask(UIViewAutoresizing.FlexibleLeftMargin);
            cell.getContentView().addSubview(button);
        } else {
            cell = getTableView().dequeueReusableCell(SOURCE_CELL_ID, indexPath);
            cell.setSelectionStyle(UITableViewCellSelectionStyle.None);
            cell.getTextLabel().setOpaque(false);
            cell.getTextLabel().setTextAlignment(NSTextAlignment.Center);
            cell.getTextLabel().setTextColor(UIColor.colorGray());
            cell.getTextLabel().setNumberOfLines(2);
            cell.getTextLabel().setHighlightedTextColor(UIColor.colorBlack());
            cell.getTextLabel().setFont(UIFont.getSystemFont(12.0));
            cell.getTextLabel().setText(this.dataSourceArray.get((int) NSIndexPathExtensions.getSection(indexPath)).getSource());
        }

        return cell;
    }

    /**
     * Create button with provided parameters
     * 
     * @param title title of button
     * @param frame frame of button
     * @param image image in button
     * @param imagePressed state to show when image is pressed
     * @param darkTextColor should text of dark color be displayed
     * @return a new UIButton
     */
    private static UIButton newButton(String title, CGRect frame, UIImage image, UIImage imagePressed,
            boolean darkTextColor) {
        UIButton button = new UIButton(frame);
        button.setContentVerticalAlignment(UIControlContentVerticalAlignment.Center);
        button.setContentHorizontalAlignment(UIControlContentHorizontalAlignment.Center);

        button.setTitle(title, UIControlState.Normal);
        if (darkTextColor) {
            button.setTitleColor(UIColor.colorBlack(), UIControlState.Normal);
        } else {
            button.setTitleColor(UIColor.colorWhite(), UIControlState.Normal);
        }

        UIImage newImage = image.newStretchable(12, 0);
        button.setBackgroundImage(newImage, UIControlState.Normal);

        UIImage newPressedImage = imagePressed.newStretchable(12, 0);
        button.setBackgroundImage(newPressedImage, UIControlState.Highlighted);

        // button.addTarget:target action:selector
        // forControlEvents:UIControlEventTouchUpInside];

        // in case the parent view draws with a custom color or gradient, use a
        // transparent color
        button.setBackgroundColor(UIColor.colorClear());

        return button;
    }

    /**
     * create the UIButtons with various background images
     * 
     * @return gray button
     */
    public UIButton getGrayButton() {
        if (grayButton == null) {
            UIImage buttonBackground = UIImage.createFromBundle("whiteButton.png");
            UIImage buttonBackgroundPressed = UIImage.createFromBundle("blueButton.png");

            CGRect frame = new CGRect(0.0, 5.0, stdButtonWidth, stdButtonHeight);
            grayButton = newButton("Gray", frame, buttonBackground, buttonBackgroundPressed, true);
            grayButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Gray button pressed!");
                }

            });

            grayButton.setTag(viewTag);
        }

        return grayButton;
    }

    /**
     * create a UIButton with just an image instead of a title
     * 
     * @return image button
     */
    public UIButton getImageButton() {
        if (imageButton == null) {
            UIImage buttonBackground = UIImage.createFromBundle("whiteButton.png");
            UIImage buttonBackgroundPressed = UIImage.createFromBundle("blueButton.png");

            CGRect frame = new CGRect(0.0, 5.0, stdButtonWidth, stdButtonHeight);
            imageButton = newButton("", frame, buttonBackground, buttonBackgroundPressed, true);
            imageButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Image button pressed");
                }
            });

            imageButton.setImage(UIImage.createFromBundle("UIButton_custom.png"), UIControlState.Normal);
            imageButton.setAccessibilityIdentifier("ArrowButton");
            imageButton.setTag(viewTag);
        }

        return imageButton;
    }

    /**
     * create a UIButton with just an image instead of a title
     * 
     * @return attributed text button
     */
    public UIButton getAttrTextButton() {
        if (attrTextButton == null) {
            // create a UIButton with attributed text for its title
            attrTextButton = UIButton.create(UIButtonType.RoundedRect);

            attrTextButton.setFrame(new CGRect(0.0, 5.0, stdButtonWidth, stdButtonHeight));
            attrTextButton.setTitle("Rounded", UIControlState.Normal);
            attrTextButton.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {

                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("AttrButton pressed!");
                }
            });

            // apply red text for normal state
            NSMutableAttributedString normalAttrString = new NSMutableAttributedString("Rounded");
            normalAttrString.addAttribute(UIKit.ForegroundColorAttributeName(), UIColor.colorRed(), new NSRange(0, normalAttrString.getLength()));
            attrTextButton.setAttributedTitle(normalAttrString, UIControlState.Normal);

            // apply green text for pressed state
            NSMutableAttributedString highlightedAttrString = new NSMutableAttributedString("Rounded");
            normalAttrString.addAttribute(new NSString("Green"), UIColor.colorGreen(), new NSRange(0, normalAttrString.getLength()));

            attrTextButton.setAttributedTitle(highlightedAttrString, UIControlState.Highlighted);
            attrTextButton.setTag(viewTag); // tag this view for later so we can
                                            // remove it from recycled table
                                            // cells
        }
        return attrTextButton;
    }

    /**
     * create a UIButton (UIButtonTypeRoundedRect)
     * 
     * @return Rounded UIButton
     */
    public UIButton getRoundedButtonType() {
        if (roundedButtonType == null) {
            roundedButtonType = UIButton.create(UIButtonType.RoundedRect);
            roundedButtonType.setFrame(new CGRect(0.0, 5.0, stdButtonWidth, stdButtonHeight));
            roundedButtonType.setTitle("Rounded", UIControlState.Normal);
            roundedButtonType.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Rounded pressed!");
                }
            });
            roundedButtonType.setTag(viewTag);
        }
        return roundedButtonType;
    }

    /**
     * create a UIButton (UIButtonTypeDetailDisclosure)
     * 
     * @return Detail button
     */
    public UIButton getDetailDisclosureButtonType() {
        if (detailDisclosureButtonType == null) {
            detailDisclosureButtonType = UIButton.create(UIButtonType.DetailDisclosure);
            detailDisclosureButtonType.setFrame(new CGRect(0.0, 8.0, 25.0, 25.0));
            detailDisclosureButtonType.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Detailed description pressed!");
                }
            });
            detailDisclosureButtonType.setTitle("Detail Disclosure", UIControlState.Normal);
            detailDisclosureButtonType.setBackgroundColor(UIColor.colorClear());
            detailDisclosureButtonType.setTag(viewTag);
        }

        return detailDisclosureButtonType;
    }

    /**
     * create a UIButton (UIButtonTypeInfoDark)
     * 
     * @return dark info button
     */
    public UIButton getInfoDarkButtonType() {
        if (infoDarkButtonType == null) {
            infoDarkButtonType = UIButton.create(UIButtonType.InfoDark);
            infoDarkButtonType.setFrame(new CGRect(0.0, 8.0, 25.0, 25.0));
            infoDarkButtonType.setTitle("Info Dark", UIControlState.Normal);
            infoDarkButtonType.setBackgroundColor(UIColor.colorClear());
            infoDarkButtonType.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Click dark info Button!");
                }
            });
            infoDarkButtonType.setTag(viewTag);
        }
        return infoDarkButtonType;
    }

    /**
     * create a UIButton (UIButtonTypeInfoLight)
     * 
     * @return light info button
     */
    public UIButton getInfoLightButtonType() {
        if (infoLightButtonType == null) {
            infoLightButtonType = UIButton.create(UIButtonType.InfoLight);
            infoLightButtonType.setFrame(new CGRect(0.0, 8.0, 25.0, 25.0));
            infoLightButtonType.setTitle("Info Light", UIControlState.Normal);
            infoLightButtonType.setBackgroundColor(UIColor.colorClear());
            infoLightButtonType.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Click light info Button!");
                }
            });
            infoLightButtonType.setBackgroundColor(UIColor.colorGray());
        }
        return infoLightButtonType;
    }

    /**
     * create a UIButton (UIButtonTypeContactAdd)
     * 
     * @return UIButton contact add
     */
    public UIButton getContactAddButtonType() {
        if (contactAddButtonType == null) {
            contactAddButtonType = UIButton.create(UIButtonType.ContactAdd);
            contactAddButtonType.setFrame(new CGRect(0.0, 8.0, 25.0, 25.0));
            contactAddButtonType.setTitle("Add Contact", UIControlState.Normal);
            contactAddButtonType.setBackgroundColor(UIColor.colorClear());
            contactAddButtonType.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    System.out.println("Click contact add Button!");
                }
            });

            contactAddButtonType.setTag(viewTag);
        }
        return contactAddButtonType;
    }

}
