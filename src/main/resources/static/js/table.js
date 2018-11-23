
/*
控制table父容器的高度，让table可以正常滑动
 */
function initTableSize() {
    document.getElementsByClassName("table_container")[0].style.height
        = (window.innerHeight - 64 - 64) + "px";
}
