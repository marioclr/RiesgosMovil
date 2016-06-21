package com.mclr.mini.riesgosmovil.utilitarios;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.R;

public class ExpandibleListViewAdapter extends BaseExpandableListAdapter {
	private final SparseArray<GrupoDeItems> grupos;
	public LayoutInflater inflater;
	public Activity activity;

	public ExpandibleListViewAdapter(Activity act, SparseArray<GrupoDeItems> grupos)
	{
	      activity = act;
	      this.grupos = grupos;
	      inflater = act.getLayoutInflater();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return grupos.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String children = (String) getChild(groupPosition, childPosition);
		TextView textvw = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expandible_subitems_layout, null);
		}
		textvw = (TextView) convertView.findViewById(R.id.textView1);
		textvw.setText(children); convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return grupos.get(groupPosition).children.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return grupos.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return grupos.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expandible_items_layout, null);
		}
		GrupoDeItems grupo = (GrupoDeItems) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(grupo.string);
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}


	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}


	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

}
