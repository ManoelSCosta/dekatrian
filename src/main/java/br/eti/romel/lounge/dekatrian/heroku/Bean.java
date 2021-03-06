/**
 * Copyright (C) 2018 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package br.eti.romel.lounge.dekatrian.heroku;

import br.eti.romel.lounge.dekatrian.*;
import java.text.*;
import java.util.*;
import lombok.*;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
public final class Bean {

    private static final SimpleDateFormat SDF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_LONG = new SimpleDateFormat("dd MMMM yyyy", new Locale("pt", "BR"));
    @Getter
    private Calendar gregorian;
    @Getter
    private String gregorianFormat;
    @Getter
    private String gregorianHuman;
    @Getter
    private DekatrianCalendar dekatrian;
    @Getter
    private String dekatrianFormat;
    @Getter
    private String dekatrianHuman;
    @Getter
    @Setter
    private String mensagem;

    public Bean() {

        this.setDekatrian(new DekatrianCalendar());
    }

    public Bean(Calendar gregorian) {

        this.setGregorian(gregorian);
    }

    public Bean(DekatrianCalendar dekatrian) {

        this(dekatrian.getGregorian());
    }

    public boolean setGregorian(Calendar gregorian) {

        return setVariables(gregorian, new DekatrianCalendar(gregorian));
    }

    public boolean setDekatrian(DekatrianCalendar dekatrian) {

        return setVariables(dekatrian.getGregorian(), dekatrian);
    }

    private boolean setVariables(Calendar gregorian, DekatrianCalendar dekatrian) {
        this.gregorian = gregorian;
        this.dekatrian = dekatrian;
        this.gregorianFormat = SDF_SHORT.format(this.gregorian.getTime());
        this.dekatrianFormat = this.dekatrian.toString();
        this.dekatrianHuman = this.dekatrian.toHuman();
        this.gregorianHuman = SDF_LONG.format(gregorian.getTime());

        return this.dekatrian.isValid();
    }
}
