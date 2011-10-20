package com.es.zumeh.client.view.pages;
public interface NodeClickNotifier {
  public void addClickListener(NodeClickListener listener);
  public void removeClickListener(NodeClickListener listener);
}