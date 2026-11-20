package io.github.abdurazaaqmohammed.ui;

import android.view.View;
import android.widget.TextView;

import io.github.abdurazaaqmohammed.MPManager.R;

/** Builds one combined TalkBack announcement per list_file row: actual name first, then date/size. */
public final class A11yRow {
    private A11yRow() {}

    public static void apply(View row, CharSequence name, CharSequence info) {
        if (row == null) return;
        if (info == null || info.length() == 0) row.setContentDescription(name);
        else row.setContentDescription(name + ", " + info);
    }

    /** Re-reads name and info from the row's child views (list_file.xml) and re-applies. */
    public static void refreshFrom(TextView fileDateView) {
        View parent = fileDateView == null ? null : (View) fileDateView.getParent();
        View row = parent == null ? null : (View) parent.getParent();
        if (row == null) return;
        TextView name = row.findViewById(R.id.fileName);
        CharSequence n = name == null ? "" : name.getText();
        CharSequence info = fileDateView.getVisibility() == View.VISIBLE ? fileDateView.getText() : "";
        apply(row, n, info);
    }
}
