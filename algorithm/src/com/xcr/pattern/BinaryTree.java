package com.xcr.pattern;


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    private void backOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        backOrder(node.left);
        backOrder(node.right);
        System.out.println(node.val);
    }

    private List<Integer> preOrderTraversal(TreeNode node) {
        if (node == null) {
            return new LinkedList<>();
        }
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.removeFirst();
            res.add(treeNode.val);
            if (treeNode.right != null) {
                stack.addFirst(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.addFirst(treeNode.left);
            }
        }
        return res;
    }

    private List<Integer> inOrderTraversal(TreeNode node) {
        if (node == null) {
            return new LinkedList<>();
        }
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode root = node;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addLast(root);
                root = root.left;
            }
            root = stack.removeLast();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    private List<Integer> backOrderTraversal(TreeNode node) {
        if (node == null) {
            return new LinkedList<>();
        }
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.removeFirst();
            res.addFirst(treeNode.val);
            if (treeNode.left != null) {
                stack.addFirst(treeNode.left);
            }
            if (treeNode.right != null) {
                stack.addFirst(treeNode.right);
            }
        }
        return res;
    }

    private List<Integer> dfsUpToDown(TreeNode node) {
        LinkedList<Integer> res = new LinkedList<>();
        dfs(node, res);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) { return; }
        res.add(node.val);
        dfs(node.left, res);
        dfs(node.right, res);
    }

    public List<Integer> prerderTraversal(TreeNode root) {
        return divideAndConquer(root);
    }

    private List<Integer> divideAndConquer(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        if (node == null) {
            return null;
        }
        List<Integer> left = divideAndConquer(node.left);
        List<Integer> right = divideAndConquer(node.right);
        // 合并结果
        result.add(node.val);
        if (left != null) {
            result.addAll(left);
        }
        if (right != null) {
            result.addAll(right);
        }
        return result;
    }

    public List<Integer> bfsOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                res.add(node.val);
                if ( node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    // 给定一个二叉树，找出其最大深度
    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    private Boolean result = Boolean.TRUE;
    // 给定一个二叉树，判断它是否是高度平衡的二叉树
    private Boolean isBalanced(TreeNode node) {
        getMaxDepth(node);
        return result;
    }

    private int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(node.left);
        int rightDepth = getMaxDepth(node.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            result = Boolean.FALSE;
        }
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // 给定一个非空二叉树，返回其最大路径和
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode node) {
        maxGain(node);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左右字节点的最大贡献值，只有在最大贡献值大于0时，才会选取对应的子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int curMaxSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, curMaxSum);
        return maxSum;
    }

    public static void main(String[] args) {
        TreeNode node = new BinaryTree.TreeNode(1);
        node.left = new BinaryTree.TreeNode(2);
        node.right = new BinaryTree.TreeNode(3);
        node.left.left = new BinaryTree.TreeNode(4);
        node.left.right = new BinaryTree.TreeNode(5);
        node.right.left = new BinaryTree.TreeNode(6);
        node.right.right = new BinaryTree.TreeNode(7);

        BinaryTree binaryTree = new BinaryTree();
//        binaryTree.preOrder(node);
//        binaryTree.inOrder(node);
//        binaryTree.backOrder(node);

//        binaryTree.preOrderTraversal(node);
//        binaryTree.inOrderTraversal(node);
//        List<Integer> integers = binaryTree.backOrderTraversal(node);
//        System.out.println(Collections.singletonList(integers));
        int i = binaryTree.maxDepth(node);
        System.out.println(i);
        Boolean balanced = binaryTree.isBalanced(node);
        System.out.println(balanced);
    }

}
