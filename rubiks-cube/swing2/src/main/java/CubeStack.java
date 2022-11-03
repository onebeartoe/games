//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Label;
import java.util.EmptyStackException;
import java.util.Stack;

class CubeStack {
    private int Watermark = 0;
    private int Count = 0;
    private Stack UndoStack = new Stack();
    private Stack RedoStack = new Stack();
    private Label StepText;
    private boolean UpdateEnabled = true;

    public void EnableUpdate(boolean enabled) {
        this.UpdateEnabled = enabled;
        if (this.UpdateEnabled) {
            this.UpdateStep();
        }

    }

    CubeStack(Label steptext) {
        this.StepText = steptext;
    }

    void Do(CubeAction action) {
        this.UndoStack.push(action);
        if (!this.RedoStack.empty()) {
            this.RedoStack = new Stack();
        }

        ++this.Count;
        this.UpdateStep();
    }

    public void UpdateStep() {
        if (this.UpdateEnabled) {
            this.StepText.setText(String.valueOf(this.Count - this.Watermark));
        }

    }

    CubeAction Undo() {
        if (this.Count > this.Watermark) {
            CubeAction newaction;
            try {
                newaction = (CubeAction)this.UndoStack.pop();
            } catch (EmptyStackException var4) {
                return null;
            }

            this.RedoStack.push(newaction);
            this.Count += -1;
            this.UpdateStep();
            return newaction;
        } else {
            return null;
        }
    }

    void Clear() {
        while(!this.UndoStack.empty()) {
            this.UndoStack.pop();
        }

        while(!this.RedoStack.empty()) {
            this.RedoStack.pop();
        }

        this.Watermark = 0;
        this.Count = 0;
        this.UpdateStep();
    }

    void SetWatermark() {
        this.Watermark = this.Count;
        this.UpdateStep();
    }

    CubeAction ForceUndo() {
        CubeAction newaction;
        try {
            newaction = (CubeAction)this.UndoStack.pop();
        } catch (EmptyStackException var4) {
            return null;
        }

        this.Watermark += -1;
        this.Count += -1;
        if (!this.RedoStack.empty()) {
            this.RedoStack = new Stack();
        }

        this.UpdateStep();
        return newaction;
    }

    CubeAction Redo() {
        CubeAction newaction;
        try {
            newaction = (CubeAction)this.RedoStack.pop();
        } catch (EmptyStackException var4) {
            return null;
        }

        ++this.Count;
        this.UpdateStep();
        return (CubeAction)this.UndoStack.push(newaction);
    }
}
