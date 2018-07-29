Web development

Interpolation: whenever you need to communicate properties(variables, objects, arrays, etc) *from the component class to the template*, you can use interpolation.

The format for defining interpolation in a template is: {{propertyName}}

in the ts file of components:

```js
export class HomeComponent implements OnInit {
  itemCount: number = 4;
	constructor() {}
	ngOnInit() {}
}
```

in the html file of corresponding components:

##Interpolation##

```html
<p>
  Your bucket list ({{itemCount}})
</p>

```

## property binding##

Let's say for some reason, that we want ot use the component class to control the value of our Add Item button text. We can do this through property binding.

in the ts file of components:

```js
itemCount: number=4;
btnText: string = "Add an item"
```

in the html file:

the first way to wire up property textContent (which is a native property for tracking text content in an element) with :

Using the property binding syntax of the square brackets tells angular that you want it to evaluate an expression for that property. removing the square brackets would let Angular no longer evaluate the quoted value as an expression.

```html
<h2 [textContent] = "itemCount"></h2>
```

the second way to wire up property:

```html
<h2 textContent = "{{itemCount}}"></h2>
```

## Event Binding##

```html
<a class="delete" (click)="onDelete()">
  remove
</a>
```

in the corresponding media-item.component.ts file:

```typescript
import {{Component, Input}} from '@angular/core'
@Component({
  selector: 'mw-media-item',
    templateUrl: '',
    styleUrl: []
})
export class MediaItemComponent{
  @Input() mediaItem; //this will tell angular we want it to support any property bindings placed on instances of the mw-media-item elements where the property name is mediaItem
  onDelete(){
    console.log('deleted');
  } 
}
```

When Angular parses the property syntax in a template, it is going to find either a match on a known DOM property named for an element OR a property decorated by the INPUT decorator. The DOM has built-in properties on existing elements. Components you create do not.



that is why the input decorator is. used to give component properties that you want to expose for use when using the component.





## structural directive Ngif##

Astreik usage in Ngif

```html
<h2>
  {{mediaItem.name}}
</h2>
<div *ngIf="mediaItem.watchedOn">Watched on {{mediaItem.watchedOn}}
</div>
```

Equals to

```html
<h2>
  {{mediaItem.name}}
</h2>
<ng-template [ngIf]="mediaItem.watchedOn">
	<div>
       Watched on {{mediaItem.watchedOn}}
  </div>
</ng-template>
```

Note that both ways work the same,  the same element ng-template is sth that never makes it to the DOM. It's handled by angular component template parsing.



## ngFor







## Angular component

A component in angular is just a directive with a template.

The input decorator can be used on a directive class property just like on a component class property.

