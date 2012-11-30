package project;

public abstract class PrimesModel {
	protected final UI ui;
	private final String name;

	protected PrimesModel(UI ui, String name) {
		this.ui = ui;
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final UI getUI() {
		return ui;
	}

	@Override
	public final String toString() {
		return getName();
	}
}
