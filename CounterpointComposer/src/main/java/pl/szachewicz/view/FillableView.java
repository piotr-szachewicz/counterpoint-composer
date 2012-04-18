package pl.szachewicz.view;

public interface FillableView {
	void fillViewFromModel(Object model);
	void fillModelFromView(Object model);
}
