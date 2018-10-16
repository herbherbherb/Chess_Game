package model;

public class Coordinate{
    public int x;
    public int y;

    public Coordinate(int x, int y){
        this.x = x; this.y = y;
    }
    /**
     * @param other the target coordinate to comare with
     * @return true if the coordinate exactly matches
     */

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Coordinate) || other == null) return false;
        else return (((Coordinate) other).x == this.x && ((Coordinate) other).y == this.y)?true:false;
    }

    @Override
    public String toString() { return "(" + this.x + "," + this.y + ")";}

//    /**
//     * Compare two coordinates in row-major order
//     * @param other target coordinate to be compared with
//     * @return -1 of current coordinate is less than target in row-major order; 1 otherwise
//     */
//    @Override
//    public int compareTo(Coordinate other) {
//        int current = this.x * width + this.y;
//        int target = other.x * width + other.y;
//        if(current == target) return 0;
//        else return (current < target)?-1:1;
//    }
}
