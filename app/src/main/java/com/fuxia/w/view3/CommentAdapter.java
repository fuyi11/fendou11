package com.fuxia.w.view3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fuxia.w.R;

import java.util.List;

/**
 * Created by fuyi on 2016/12/27.
 */

public class CommentAdapter extends BaseAdapter {

    CommentViewHolderItem commentViewHolderItem;
    LayoutInflater layoutInflater = null;
    private Context context;
    private List<Comment> comments;

    public CommentAdapter () {}
    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }


    /* metodo che cicla fino alla fine dell'ArrayList di commenti */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.comment_row, null);

            /* introduco un ViewHolder che contenga tutte le TextView relative ai campi di Post */

            commentViewHolderItem = new CommentViewHolderItem();
            commentViewHolderItem.commentIdView = (TextView) convertView.findViewById(R.id.commentId);
            commentViewHolderItem.nameView = (TextView) convertView.findViewById(R.id.name);
            commentViewHolderItem.emailView = (TextView) convertView.findViewById(R.id.email);
            commentViewHolderItem.bodyView = (TextView) convertView.findViewById(R.id.comments_body);
            convertView.setTag(commentViewHolderItem);
        }
        else {
         /* uso la stessa istanza di viewHolder per tutti gli elementi dell'array */

            /* prende la struttura di una comment_row e la ricicla per ogni elemento, una volta mostrati
             * i suoi dati a video */

            commentViewHolderItem = (CommentViewHolderItem) convertView.getTag();
        }
        Comment comment = (Comment) getItem(position);

        if (comment != null) {
            commentViewHolderItem.commentIdView.setText(String.valueOf(comment.getCommentId()));
            commentViewHolderItem.nameView.setText(comment.getName());
            commentViewHolderItem.emailView.setText(comment.getEmail());
            commentViewHolderItem.bodyView.setText(comment.getBody());
        }
        return convertView;
    }

    class CommentViewHolderItem {
        public TextView commentIdView;
        public TextView nameView;
        public TextView emailView;
        public TextView bodyView;
    }
}
