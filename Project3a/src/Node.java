import java.util.Map;
    public class Node<T> implements Comparable<Node<T>>{
        private final T value;
        private final int frequency;
        private final Node leftSon;
        private final Node rightSon;

        public Node(T value, int frequency){
            this.leftSon = null;
            this.rightSon = null;
            this.value = value;
            this.frequency = frequency;
        }

        public Node(Node leftSon, Node rightSon){
            this.leftSon = leftSon;
            this.rightSon = rightSon;
            this.value = null;
            this.frequency = leftSon.frequency + rightSon.frequency;
        }

        public boolean isLeaf() {return (value!=null);}

        @Override
        public int compareTo(Node node) {
            return this.frequency - node.frequency;
        }

        public void fillPathMap(Map<T, String> pathMap){
            fillPathMap(pathMap, new StringBuilder());
        }

        private void fillPathMap(Map<T, String> pathMap, StringBuilder code){
            if(this.isLeaf()){
                pathMap.put(this.value,code.toString());
            }
            else{
                StringBuilder leftCode = new StringBuilder();
                leftCode.append(code).append("0");
                leftSon.fillPathMap(pathMap,leftCode);

                StringBuilder rightCode = new StringBuilder();
                rightCode.append(code).append("1");
                rightSon.fillPathMap(pathMap,rightCode);
            }
        }
    }
