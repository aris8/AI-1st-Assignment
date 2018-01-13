import java.util.ArrayList;
import java.util.List;

public class Node<Attribute> {
    private List<Node<Attribute>> children = new ArrayList<Node<Attribute>>();
    private Node<Attribute> parent = null;
    private Attribute data = null;

    public Node(Attribute data) {
        this.data = data;
    }

    public Node(Attribute data, Node<Attribute> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<Attribute>> getChildren() {
        return children;
    }

    public void setParent(Node<Attribute> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(Attribute data) {
        Node<Attribute> child = new Node<Attribute>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node<Attribute> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public Attribute getData() {
        return this.data;
    }

    public void setData(Attribute data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0) 
            return true;
        else 
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }
}

