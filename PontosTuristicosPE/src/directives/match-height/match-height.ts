import {
  Directive, ElementRef, AfterViewChecked,
  Input, HostListener
} from '@angular/core';

@Directive({
  selector: '[myMatchHeight]' // Attribute selector
})
export class MatchHeightDirective implements AfterViewChecked {

  @Input()
  myMatchHeight: string;
  @HostListener('window:resize') 
  
  onResize() {
      // call our matchHeight function here
      this.matchHeight(this.el.nativeElement, this.myMatchHeight);
  }

  constructor(private el: ElementRef) {
  }

  ngAfterViewChecked() {
    // call our matchHeight function here later
    this.matchHeight(this.el.nativeElement, this.myMatchHeight);
  }

  matchHeight(parent: HTMLElement, className: string) {
    // match height logic here
    if (!parent) return;
    // console.log('parent ' + JSON.stringify(parent));
    // step 1: find all the child elements with the selected class name
    const children = parent.getElementsByClassName(className);

    if (!children) return;
    Array.from(children).forEach((x: HTMLElement) => {
      x.style.height = 'initial';
    });
    // console.log('ch ' + JSON.stringify(children));
    // step 2a: get all the child elements heights
    const itemHeights = Array.from(children)
      .map(x => x.getBoundingClientRect().height);

    // step 2b: find out the tallest
    const maxHeight = itemHeights.reduce((prev, curr) => {
      return curr > prev ? curr : prev;
    }, 0);

    // console.log('maxh = ' + maxHeight);
    // step 3: update all the child elements to the tallest height
    Array.from(children)
      .forEach((x: HTMLElement) => x.style.height = `${maxHeight}px`);
  }
}