package io.github.abdurazaaqmohammed.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.io.Files;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import io.github.abdurazaaqmohammed.MPManager.R;
import io.github.abdurazaaqmohammed.utils.FileUtils;
import io.github.abdurazaaqmohammed.utils.SignatureKeyDialog;

public class UIHelper {
    private final AppCompatActivity context;

    public int dp(int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public UIHelper (AppCompatActivity context) {
        this.context = context;
    }

    public View.OnClickListener showSignSettingsDialog() {
        return v -> SignatureKeyDialog.show(context);
    }

    public static String radioGroupValue(RadioGroup group, String defaultValue) {
        int id = group.getCheckedRadioButtonId();
        if (id == -1) return defaultValue;
        RadioButton rb = group.findViewById(id);
        Object tag = rb.getTag();
        return (tag == null) ? defaultValue : tag.toString();
    }

    public RadioButton makeRadioButton(String value, String label) {
        RadioButton rb = new RadioButton(context);
        rb.setText(label);
        rb.setId(View.generateViewId());
        rb.setTag(value);
        return rb;
    }

    public static void selectRadioByValue(RadioGroup group, String valueToSelect) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);
            if (child instanceof RadioButton rb) {
                Object tag = rb.getTag();
                if (tag != null && tag.toString().equals(valueToSelect)) {
                    group.check(rb.getId());
                    return;
                }
            }
        }
        if (group.getCheckedRadioButtonId() == -1 && group.getChildCount() > 0) {
            RadioButton first = (RadioButton) group.getChildAt(0);
            group.check(first.getId());
        }
    }

    public void styleEditText(EditText editText) {
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setTextColor(Color.WHITE);
        editText.setHintTextColor(Color.GRAY);
    }

    public void scrollTextView(TextView textView) {
        textView.setSingleLine(true);

        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setMarqueeRepeatLimit(-1);
        textView.setHorizontallyScrolling(true);
        textView.setSelected(true); // Start without focus
    }

    public LinearLayout getProperties() {
        LinearLayout parentLayout = new LinearLayout(context);
        parentLayout.setOrientation(LinearLayout.HORIZONTAL);
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        LinearLayout firstVerticalLayout = new LinearLayout(context);
        firstVerticalLayout.setOrientation(LinearLayout.VERTICAL);
        firstVerticalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        String[] labels = {"Name", "Parent", "Type", "Size", "Modified", "Permissions", "Owner", "Group"};
        for (String label : labels) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setText(label);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(10, 10, 10, 10);
            firstVerticalLayout.addView(textView);
        }

        LinearLayout secondVerticalLayout = new LinearLayout(context);
        secondVerticalLayout.setOrientation(LinearLayout.VERTICAL);
        secondVerticalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        for (int i = 0; i < labels.length; i++) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setTextColor(Color.WHITE);
            textView.setPadding(10, 10, 10, 10);
            secondVerticalLayout.addView(textView);
        }

        parentLayout.addView(firstVerticalLayout);
        parentLayout.addView(secondVerticalLayout);
        return parentLayout;
    }
}