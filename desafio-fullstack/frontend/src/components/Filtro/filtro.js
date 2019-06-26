import React, { Component } from 'react'
import { MDCChipSet } from '@material/chips';

export default class Filtro extends Component {
    render() {
        return (
            <div class="mdc-chip-set mdc-chip-set--filter">
                <div class="mdc-chip">
                    <i class="material-icons mdc-chip__icon mdc-chip__icon--leading">face</i>
                    <i class="material-icons mdc-chip__icon mdc-chip__icon--leading">face</i>
                    <i class="material-icons mdc-chip__icon mdc-chip__icon--leading">face</i>
                    <div class="mdc-chip__text">Filterable content</div>
                </div>
            </div>
        )
    }
}
