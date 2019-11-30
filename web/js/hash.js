function hash(string, g = 79) {
    let hash = 0;

    for (let i = 0; i < string.length; i++) {
        hash = g * hash + string.charCodeAt(i);
    }

    return hash;
}
