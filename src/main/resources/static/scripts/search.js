/**
 * 
 */
$(document).ready(function() {

Array.prototype.forEach.call(document.querySelectorAll('.clearable-input'), function(el) {
 var input = el.querySelector('input');

 conditionallyHideClearIcon();
 input.addEventListener('input', conditionallyHideClearIcon);
 el.querySelector('[data-clear-input]').addEventListener('click', function(e) {
   input.value = '';
   conditionallyHideClearIcon();
   $('#searchForm').submit();
 });

 function conditionallyHideClearIcon(e) {
   var target = (e && e.target) || input;
   target.nextElementSibling.style.display = target.value ? 'block' : 'none';
 }
});

});