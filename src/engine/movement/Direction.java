package engine.movement;
import javafx.scene.image.Image;
import java.util.function.Consumer;
import java.util.function.Function;

import data.player.Player;

public enum Direction {

	UP(e -> e.emerald_up_rest, e -> e.emerald_up_1, e -> e.emerald_up_2, Player::moveUp),
	DOWN(e -> e.emerald_down_rest, e -> e.emerald_down_1, e -> e.emerald_down_2, Player::moveDown),
	LEFT(e -> e.emerald_left_rest, e -> e.emerald_left_1, e -> e.emerald_left_2, Player::moveLeft),
	RIGHT(e -> e.emerald_right_rest, e -> e.emerald_right_1, e -> e.emerald_right_2, Player::moveRight);

	public final Function<Player, Image> image, image1, image2;
	public final Consumer<Player> move;

	Direction(Function<Player, Image> image, Function<Player, Image> image1,
	          Function<Player, Image> image2, Consumer<Player> move) {
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.move = move;
	}

	public static final Direction[] cachedValues = values();

}
