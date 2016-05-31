package com.leruka.leruka.res;

/**
 * Created by leifb on 11.05.16.
 */
class ResourceDescription {

    private int id;
    private int size;

    ResourceDescription(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceDescription that = (ResourceDescription) o;

        if (id != that.id) return false;
        return size == that.size;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + size;
        return result;
    }
}
