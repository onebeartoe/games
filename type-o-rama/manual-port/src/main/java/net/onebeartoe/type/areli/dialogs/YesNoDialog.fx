
package net.onebeartoe.type.areli.dialogs;

import javafx.scene.CustomNode;
import javafx.animation.KeyFrame;
import javafx.scene.Node;

public abstract class YesNoDialog extends CustomNode
{

    public var width: Integer;

    public var height: Integer;

    public var yesButtonX: Integer;

    public var yesButtonY: Integer;

    public var noButtonX: Integer;

    public var noButtonY: Integer;

    public var removeFrame: KeyFrame;

    public var yesButton: Node;

    public var noButton: Node;

    public var title: String = "Congrats!";

    public var message: String = "Some yes or no question?";

    public var messageX: Integer = 30;

    public abstract function getWords(): String [];

    public abstract function onWackaWacka(): Void;

//    public var yesAction;

//    public abstract attribute yesAction(): Void
    public abstract function yesAction(): Void

//    public function yesAction(): Void
//    {}


}
