package com.views.components;

import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import com.constants.ConfigUI;
import com.utils.UIFactory;

public class TSpinner extends JSpinner {
   public TSpinner() {
      super();
      initComponents();
   }

   public TSpinner(SpinnerModel model) {
      super(model);
      initComponents();
   }

   private void initComponents() {
      setBorder(UIFactory.combineBorderPadding(getBorder(), new Insets(ConfigUI.Padding.TINY,
            ConfigUI.Padding.SMALL, ConfigUI.Padding.TINY, ConfigUI.Padding.SMALL)));
   }
}
