package cutword.util;

public class test {
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static void main(String[] args) {
        System.out.println(ParseString.clearNotChinese("我是  王明军123,.,  "));
    }
    public boolean isValidBST(TreeNode root) {
        Info res = recur(root);
        return res.isBST;
    }

    private Info recur(TreeNode root) {
      if(root==null){
          return new Info(true,Long.MIN_VALUE,Long.MAX_VALUE);
      }
      boolean isBST;
      long max;
      long min;
      Info l = recur(root.left);
      Info r = recur(root.right);
      isBST = (l.isBST)&&(r.isBST)&&l.max<root.val&&r.min>root.val;
      max = Math.max(Math.max(root.val,l.max),r.max);
      min = Math.min(Math.min(root.val,l.min),r.min);
      return new Info(isBST,max,min);

    }

    class Info{
      boolean isBST;
     long max;
     long min;

        public Info(boolean isBST, long max, long min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
