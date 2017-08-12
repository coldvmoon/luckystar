import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'findLanguageFromKey'})
export class FindLanguageFromKeyPipe implements PipeTransform {
    private languages: any = {
        'zh-cn': { name: '中文（简体）' }
        // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    };
    transform(lang: string): string {
        return this.languages[lang].name;
    }
}

@Pipe({name: 'timeFormat'})
export class TimeFormat implements PipeTransform {
    transform(value: string): string {
        let time: number = parseInt(value);
        let second: number = Math.floor(time%60);
        let hour: number = Math.floor(time/3600);
        time = time - 3600*hour;
        let minute: number = Math.floor(time/60);
        let str ="";
        if(hour>0){
            str+=hour+"时";
        }
        if(minute>=0){
            str+=minute+"分";
        }
        if(second>=0){
            str+=second+"秒";
        }
        return str;
    }
}
