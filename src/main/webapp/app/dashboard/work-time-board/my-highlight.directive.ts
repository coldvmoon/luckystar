import {Directive, ElementRef, HostListener, Input, OnDestroy, OnInit} from '@angular/core';

@Directive({
    selector: '[myHighlight]'
})
export class HighlightDirective implements OnInit, OnDestroy  {
    ngOnInit(): void {
        if(this.highlightColor==0){
            this.highlight("red");
        }else if (this.highlightColor<60*60*4){
            this.highlight("yellow");
        }
    }

    ngOnDestroy(): void {
    }

    constructor(private el: ElementRef) {

    }

    @Input() highlightColor: any;


    private highlight(color: string) {
        this.el.nativeElement.style.backgroundColor = color;
    }

}
